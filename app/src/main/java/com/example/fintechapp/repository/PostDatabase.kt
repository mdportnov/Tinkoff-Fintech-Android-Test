package com.example.fintechapp.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fintechapp.Utils
import com.example.fintechapp.model.Post

@Database(entities = [Post::class], version = 1)
abstract class PostDatabase : RoomDatabase() {

    abstract fun postDao() : PostDAO

    companion object {
        @Volatile
        private var INSTANCE: PostDatabase? = null

        fun getDatabaseClient(context: Context) : PostDatabase {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, PostDatabase::class.java, Utils.DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries() // !!!!!!!!!!!!!!!!!
                    .build()
                return INSTANCE!!
            }
        }
    }
}