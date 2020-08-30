package com.example.fintechapp.repository.top

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fintechapp.Utils
import com.example.fintechapp.model.Post
import com.example.fintechapp.repository.PostDAO

@Database(entities = [Post::class], version = 1)
abstract class TopPostDatabase : RoomDatabase() {

    abstract fun postDao() : PostDAO

    companion object {
        @Volatile
        private var INSTANCE: TopPostDatabase? = null

        fun getDatabaseClient(context: Context) : TopPostDatabase {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, TopPostDatabase::class.java, Utils.DB_TOP_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                return INSTANCE!!
            }
        }
    }
}