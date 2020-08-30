package com.example.fintechapp.repository.top

import android.content.Context
import com.example.fintechapp.model.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class TopPostRepository {
    companion object {
        private var postDatabase: TopPostDatabase? = null

        fun initDB(context: Context): TopPostDatabase {
            return TopPostDatabase.getDatabaseClient(context)
        }

        fun insertData(context: Context, newPost: Post) {
            postDatabase = initDB(context)

            CoroutineScope(IO).launch {
                postDatabase!!.postDao().insert(newPost)
            }
        }

        fun getPostById(context: Context, id: Int): Post {
            postDatabase = initDB(context)
            return postDatabase!!.postDao().getPostByDBId(id)
        }

        fun getAllPosts(context: Context): List<Post?>? {
            postDatabase = initDB(context)
            return postDatabase!!.postDao().getAll()
        }

        fun deleteAllPosts(context: Context) {
            postDatabase = initDB(context)
            postDatabase!!.postDao().deleteAllPosts()
        }

        fun getSize(context: Context): Int {
            postDatabase = initDB(context)
            return postDatabase!!.postDao().getSize()
        }

        fun resetAI(context: Context) {
            postDatabase = initDB(context)
            postDatabase!!.postDao().resetAI()
        }
    }
}