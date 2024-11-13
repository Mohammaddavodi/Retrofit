package com.example.retrofit

import com.google.gson.annotations.SerializedName

data class AlbumItem (
    @SerializedName("userId")
    val userId:Int,
    @SerializedName("id")
    val id:Int,
    @SerializedName("title")
    val title:String
)
