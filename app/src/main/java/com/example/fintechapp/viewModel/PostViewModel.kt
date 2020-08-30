package com.example.fintechapp.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.fintechapp.model.Post
import com.example.fintechapp.repository.PostRepository


class PostViewModel : ViewModel() {

    fun insertData(context: Context, newPost: Post) {
        PostRepository.insertData(context, newPost)
    }

    fun getPost(context: Context, id: Int): Post {
        return PostRepository.getPostById(context, id)
    }

    fun getAllPosts(context: Context): List<Post?>? {
        return PostRepository.getAllPosts(context)
    }

    fun deleteAllPosts(context: Context) {
        PostRepository.deleteAllPosts(context)
    }

    fun getSize(context: Context): Int {
        return PostRepository.getSize(context)
    }

    fun resetAI(context: Context) {
        PostRepository.resetAI(context)
    }
}