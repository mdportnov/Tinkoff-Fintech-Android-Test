package com.example.fintechapp.repository

import androidx.room.*
import com.example.fintechapp.Utils
import com.example.fintechapp.model.Post

@Dao
interface PostDAO {
    @Query("SELECT * FROM ${Utils.DB_RANDOM_NAME}")
    fun getAll(): List<Post>

    @Query("SELECT * FROM ${Utils.DB_RANDOM_NAME} WHERE DBid = :id")
    fun getPostByDBId(id: Int): Post

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: Post)

    @Query("DELETE FROM ${Utils.DB_RANDOM_NAME}")
    fun deleteAllPosts()

    @Query("SELECT COUNT() FROM ${Utils.DB_RANDOM_NAME}")
    fun getSize(): Int

    @Query("DELETE FROM sqlite_sequence")
    fun resetAI()

}