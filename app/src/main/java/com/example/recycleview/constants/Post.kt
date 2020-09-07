package com.example.recycleview.constants

import com.google.gson.annotations.SerializedName


class Post(
    @SerializedName ("title") var postTitle: String,
    @SerializedName ("body") var postDescription: String
)