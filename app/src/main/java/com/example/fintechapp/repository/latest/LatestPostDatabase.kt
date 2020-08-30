package com.example.fintechapp.repository.latest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fintechapp.Utils
import com.example.fintechapp.model.Post
import com.example.fintechapp.repository.PostDAO

@Database(entities = [Post::class], version = 1)
abstract class LatestPostDatabase : RoomDatabase() {

    abstract fun postDao() : PostDAO

    companion object {
        @Volatile
        private var INSTANCE: LatestPostDatabase? = null

        fun getDatabaseClient(context: Context) : LatestPostDatabase {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, LatestPostDatabase::class.java, Utils.DB_LATEST_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                return INSTANCE!!
            }
        }
    }
}