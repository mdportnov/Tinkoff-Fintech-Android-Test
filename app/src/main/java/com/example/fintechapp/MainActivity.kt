package com.example.fintechapp

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.fintechapp.adapters.mPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
//    lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.custom_toolbar)

        val mPagerAdapter = mPagerAdapter(supportFragmentManager)
        viewpager.adapter = mPagerAdapter
        tab_layout.setupWithViewPager(viewpager)

//        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
    }
}