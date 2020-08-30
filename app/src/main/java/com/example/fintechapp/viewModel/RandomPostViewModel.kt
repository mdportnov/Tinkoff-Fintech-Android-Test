package com.example.fintechapp.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.fintechapp.model.Post
import com.example.fintechapp.repository.random.RandomPostRepository


class RandomPostViewModel : ViewModel() {

    fun insertData(context: Context, newPost: Post) {
        RandomPostRepository.insertData(context, newPost)
    }

    fun getPost(context: Context, id: Int): Post {
        return RandomPostRepository.getPostById(context, id)
    }

    fun getAllPosts(context: Context): List<Post?>? {
        return RandomPostRepository.getAllPosts(context)
    }

    fun deleteAllPosts(context: Context) {
        RandomPostRepository.deleteAllPosts(context)
    }

    fun getSize(context: Context): Int {
        return RandomPostRepository.getSize(context)
    }

    fun resetAI(context: Context) {
        RandomPostRepository.resetAI(context)
    }
}