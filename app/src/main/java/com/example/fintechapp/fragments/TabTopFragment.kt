package com.example.fintechapp.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.fintechapp.FetchCompleteListener
import com.example.fintechapp.OkHttpRequest
import com.example.fintechapp.R
import com.example.fintechapp.Utils
import com.example.fintechapp.databinding.FragmentTabTopBinding
import com.example.fintechapp.viewModel.TopPostViewModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

@SuppressLint("UseCompatLoadingForDrawables")
class TabTopFragment : Fragment(), FetchCompleteListener {
    private var _binding: FragmentTabTopBinding? = null
    private val binding get() = _binding!!
    var jsonString = ""
    private var mContext: Context? = null
    private lateinit var postViewModel: TopPostViewModel

    private var currentPointer: Int = 0
    private var currentPageOnAPI = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContext = activity!!.applicationContext
        _binding = FragmentTabTopBinding.inflate(inflater, container, false)
        val view = binding.root

        initListeners()
        postViewModel = ViewModelProvider(this).get(TopPostViewModel::class.java)
        currentPointer = 0

        getPostFromAPI()
        binding.backBtn.setImageDrawable(resources.getDrawable(R.drawable.ic_round_replay_24_inactive))
        ++currentPointer

        return view
    }

    private fun initListeners() {
        binding.nextBtn.setOnClickListener() {
            YoYo.with(Techniques.Wave)
                .duration(500)
                .playOn(binding.nextBtn);

            when {
                currentPointer < 5-> {
                    binding.backBtn.isClickable = true
                    binding.backBtn.setImageDrawable(resources.getDrawable(R.drawable.ic_round_replay_24))

                    binding.nextBtn.isClickable = true
                    binding.nextBtn.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_arrow_forward_24))

                    if (currentPointer == postViewModel.getSize(mContext!!)) {
                        ++currentPointer
                        getPostFromAPI()
                    } else {
                        getPostFromDB(++currentPointer)
                    }

                    if(currentPointer==5){
                        binding.nextBtn.isClickable = false
                        binding.nextBtn.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_arrow_forward_24_inactive))
                    }
                }

                currentPointer == 1 -> {
                    binding.nextBtn.isClickable = true
                    binding.nextBtn.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_arrow_forward_24))
                }
            }

            println("Your Current Position: $currentPointer ")
        }

        binding.backBtn.setOnClickListener() {
            if (currentPointer < 5) {
                binding.nextBtn.isClickable = true
                binding.nextBtn.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_arrow_forward_24))
            }
            YoYo.with(Techniques.RotateOut)
                .duration(400)
                .playOn(binding.backBtn);
            YoYo.with(Techniques.RotateIn)
                .duration(400)
                .playOn(binding.backBtn);

            getPrevPost()
        }


    }

    private fun getPostFromAPI() {
        val client = OkHttpClient()

        val request = OkHttpRequest(client)

        request.GET(Utils.url + "top/${currentPageOnAPI}?json=true", object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                jsonString = response.body!!.string()
                activity?.runOnUiThread {
                    this@TabTopFragment.fetchComplete()
                }
            }
        })
    }

    private fun getPrevPost() {
        if (currentPointer == 2) {
            binding.backBtn.setImageDrawable(resources.getDrawable(R.drawable.ic_round_replay_24_inactive))
            binding.backBtn.isClickable = false
            getPostFromDB(--currentPointer)
        } else {
            getPostFromDB(--currentPointer)
        }
        println("Your Current Position: $currentPointer ")
    }

    private fun getPostFromDB(num: Int) {
        val circularProgressDrawable = CircularProgressDrawable(binding.gifImage.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        val newPost = postViewModel.getPost(mContext!!, num)

        YoYo.with(Techniques.BounceIn)
            .duration(500)
            .playOn(binding.cardView)

        Glide.with(mContext!!)
            .load(newPost!!.gifUrl)
            .fitCenter()
            .placeholder(circularProgressDrawable)
            .into(DrawableImageViewTarget(binding.gifImage))

        binding.postText.text = newPost.desc
    }

    override fun fetchComplete() {
        val newFivePosts = Utils.getPostsFromJSON(jsonString)
        newFivePosts.forEach {
            postViewModel.insertData(mContext!!, it)
        }

        binding.postText.text = newFivePosts[0].desc

        val circularProgressDrawable = CircularProgressDrawable(binding.gifImage.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(mContext!!)
            .load(newFivePosts[0].gifUrl)
            .fitCenter()
            .placeholder(circularProgressDrawable)
            .into(DrawableImageViewTarget(binding.gifImage))
    }
}
