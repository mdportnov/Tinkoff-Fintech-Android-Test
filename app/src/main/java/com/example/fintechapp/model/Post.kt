package com.example.fintechapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fintechapp.Utils

@Entity(tableName = Utils.DB_RANDOM_NAME)
data class Post(
    @PrimaryKey(autoGenerate = true)
    val DBid: Long = 0,
    var APIid: Int,
    var desc: String,
    var gifUrl: String) {

    constructor(APIid: Int,
                desc: String,
                gifUrl: String) : this(0, APIid, desc, gifUrl)
}