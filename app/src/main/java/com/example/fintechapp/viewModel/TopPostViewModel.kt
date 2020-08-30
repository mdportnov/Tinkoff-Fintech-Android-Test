package com.example.fintechapp.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.fintechapp.model.Post
import com.example.fintechapp.repository.latest.LatestPostRepository
import com.example.fintechapp.repository.random.RandomPostRepository
import com.example.fintechapp.repository.top.TopPostRepository


class TopPostViewModel : ViewModel() {

    fun insertData(context: Context, newPost: Post) {
        TopPostRepository.insertData(context, newPost)
    }

    fun getPost(context: Context, id: Int): Post {
        return TopPostRepository.getPostById(context, id)
    }

    fun getAllPosts(context: Context): List<Post?>? {
        return TopPostRepository.getAllPosts(context)
    }

    fun deleteAllPosts(context: Context) {
        TopPostRepository.deleteAllPosts(context)
    }

    fun getSize(context: Context): Int {
        return TopPostRepository.getSize(context)
    }

    fun resetAI(context: Context) {
        TopPostRepository.resetAI(context)
    }
}