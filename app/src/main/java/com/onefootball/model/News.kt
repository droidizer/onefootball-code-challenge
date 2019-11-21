package com.onefootball.model

import com.google.gson.annotations.SerializedName

data class News(

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("image_url")
    val imageURL: String,

    @field:SerializedName("resource_name")
    val resourceName: String,

    @field:SerializedName("resource_url")
    val resourceURL: String,

    @field:SerializedName("news_link")
    val newsLink: String
)