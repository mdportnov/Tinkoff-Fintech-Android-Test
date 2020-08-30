package com.example.fintechapp.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.fintechapp.model.Post
import com.example.fintechapp.repository.latest.LatestPostRepository
import com.example.fintechapp.repository.random.RandomPostRepository


class LatestPostViewModel : ViewModel() {

    fun insertData(context: Context, newPost: Post) {
        LatestPostRepository.insertData(context, newPost)
    }

    fun getPost(context: Context, id: Int): Post {
        return LatestPostRepository.getPostById(context, id)
    }

    fun getAllPosts(context: Context): List<Post?>? {
        return LatestPostRepository.getAllPosts(context)
    }

    fun deleteAllPosts(context: Context) {
        LatestPostRepository.deleteAllPosts(context)
    }

    fun getSize(context: Context): Int {
        return LatestPostRepository.getSize(context)
    }

    fun resetAI(context: Context) {
        LatestPostRepository.resetAI(context)
    }
}