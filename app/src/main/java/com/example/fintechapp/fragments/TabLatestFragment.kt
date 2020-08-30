package com.example.fintechapp.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.example.fintechapp.FetchCompleteListener
import com.example.fintechapp.OkHttpRequest
import com.example.fintechapp.R
import com.example.fintechapp.Utils
import com.example.fintechapp.databinding.FragmentTabLatestBinding
import com.example.fintechapp.viewModel.PostViewModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

@SuppressLint("UseCompatLoadingForDrawables")
class TabLatestFragment : Fragment(), FetchCompleteListener {
    private var _binding: FragmentTabLatestBinding? = null
    private val binding get() = _binding!!
    var jsonString = ""
    private var mContext: Context? = null
    private lateinit var postViewModel: PostViewModel

    private var currentPointer: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContext = activity!!.applicationContext
        _binding = FragmentTabLatestBinding.inflate(inflater, container, false)
        val view = binding.root

        initListeners()
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        currentPointer = postViewModel.getSize(context!!)

        if (currentPointer == 0) {
            getPostFromAPI()
            binding.prev.setImageDrawable(resources.getDrawable(R.drawable.ic_round_replay_24_inactive))
            ++currentPointer
        } else {
            getPostFromDB(currentPointer)
        }

        return view
    }

    private fun initListeners() {
        binding.next.setOnClickListener() {
            if (currentPointer >= 1) {
                binding.prev.isClickable = true
                binding.prev.setImageDrawable(resources.getDrawable(R.drawable.ic_round_replay_24))
            }
            if (currentPointer == postViewModel.getSize(mContext!!)) {
                getPostFromAPI()
                ++currentPointer
            } else {
                getPostFromDB(++currentPointer)
            }
            println("Your Current Position: $currentPointer ")
        }

        binding.prev.setOnClickListener() {
//            var list: List<Post?>? = null
//            list = postViewModel.getAllPosts(mContext!!)
//            println("Database: \n")
//            list?.forEach {
//                println(it.toString())
//            }
            getPrevPost()
        }

        binding.deleteBD.setOnClickListener() {
            println("\nSize of DB: ${postViewModel.getSize(mContext!!)} element")
            postViewModel.deleteAllPosts(mContext!!)
            postViewModel.resetAI(mContext!!)

            Handler().post(Runnable { // Рестарт активности при удалении Базы Данных
                val intent = activity!!.intent
                intent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                            or Intent.FLAG_ACTIVITY_NO_ANIMATION
                )
                activity!!.overridePendingTransition(0, 0)
                activity!!.finish()
                activity!!.overridePendingTransition(0, 0)
                startActivity(intent)
            })
        }
    }

    private fun getPostFromAPI() {
        val client = OkHttpClient()

        val request = OkHttpRequest(client)

        request.GET(Utils.url + "random?json=true", object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                jsonString = response.body!!.string()
                activity?.runOnUiThread {
                    this@TabLatestFragment.fetchComplete()
                }
            }
        })
    }

    private fun getPrevPost() {
        if (currentPointer == 2) {
            binding.prev.setImageDrawable(resources.getDrawable(R.drawable.ic_round_replay_24_inactive))
            binding.prev.isClickable = false
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

        println("\n Getting post: \n ${postViewModel.getPost(mContext!!, num)}")
        val newPost = postViewModel.getPost(mContext!!, num)


        Glide.with(mContext!!)
            .load(newPost.gifUrl)
            .fitCenter()
            .placeholder(circularProgressDrawable)
            .into(DrawableImageViewTarget(binding.gifImage))

        binding.postText.text = newPost.desc
    }

    override fun fetchComplete() {
        val newPost = Utils.getPostFromJSON(jsonString)
        binding.postText.text = newPost.desc

        val circularProgressDrawable = CircularProgressDrawable(binding.gifImage.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(mContext!!)
            .load(newPost.gifUrl)
            .fitCenter()
            .placeholder(circularProgressDrawable)
            .into(DrawableImageViewTarget(binding.gifImage))

        postViewModel.insertData(mContext!!, newPost)
    }
}
