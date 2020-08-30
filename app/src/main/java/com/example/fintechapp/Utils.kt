package com.example.fintechapp

import android.util.Log
import com.example.fintechapp.model.Post
import okhttp3.*
import org.json.JSONObject

class Utils {
    companion object {
        const val DB_NAME = "PostsDatabase"
        const val url = "https://developerslife.ru/"
        fun getPostFromJSON(jsonString: String): Post{
            Log.d("JSON: ", jsonString)
            val jsonObject = JSONObject(jsonString)

            val desc = jsonObject.getString("description")
            val id = jsonObject.getString("id").toInt()
            val gifURL = jsonObject.getString("gifURL")

            return Post(id, desc, gifURL)
        }
    }

}