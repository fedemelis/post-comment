package com.example.recycleview.manager.api

import com.example.recycleview.constants.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    fun getPostList(): retrofit2.Call<MutableList<Post>>

}