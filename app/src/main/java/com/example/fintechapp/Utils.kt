package com.example.fintechapp

import android.util.Log
import com.example.fintechapp.model.Post
import org.json.JSONArray
import org.json.JSONObject

class Utils {
    companion object {
        const val DB_RANDOM_NAME = "PostsDatabase"
        const val DB_LATEST_NAME = "LatestPostsDatabase"
        const val DB_TOP_NAME = "TopPostsDatabase"
        const val url = "https://developerslife.ru/"
        fun getPostFromJSON(jsonString: String): Post {
            Log.d("JSON: ", jsonString)
            val jsonObject = JSONObject(jsonString)

            val desc = jsonObject.getString("description")
            val id = jsonObject.getString("id").toInt()
            val gifURL = jsonObject.getString("gifURL")

            return Post(id, desc, gifURL)
        }

        fun getPostsFromJSON(jsonString: String): ArrayList<Post> {
            Log.d("JSON: ", jsonString)
            val jsonObject = JSONObject(jsonString)
            val jsonArray = jsonObject.getJSONArray("result")

            var postList: ArrayList<Post> = ArrayList()

            var i=0
            while(i<jsonArray.length()){
                val tmpJsonObject: JSONObject = jsonArray[i] as JSONObject
                val desc = tmpJsonObject.getString("description")
                val id = tmpJsonObject.getString("id").toInt()
                val gifURL = tmpJsonObject.getString("gifURL")

                postList.add(Post(id, desc, gifURL))
                i++
            }
            return postList
        }
    }

}