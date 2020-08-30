package com.example.fintechapp.repository.random

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fintechapp.Utils
import com.example.fintechapp.model.Post
import com.example.fintechapp.repository.PostDAO

@Database(entities = [Post::class], version = 1)
abstract class RandomPostDatabase : RoomDatabase() {

    abstract fun postDao() : PostDAO

    companion object {
        @Volatile
        private var INSTANCE: RandomPostDatabase? = null

        fun getDatabaseClient(context: Context) : RandomPostDatabase {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, RandomPostDatabase::class.java, Utils.DB_RANDOM_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                return INSTANCE!!
            }
        }
    }
}