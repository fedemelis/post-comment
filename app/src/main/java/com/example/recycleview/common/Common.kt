package com.example.recycleview.common

import com.example.recycleview.base.Posts.PostsDB
import com.example.recycleview.manager.api.ApiService
import com.example.recycleview.manager.api.RetrofitClientInstance

object Common {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    var modify = false
    var currentPosition = 0
    var currentTitle: String? = ""
    var currentBody: String? = ""
    var myMutableListOfPost : MutableList<PostsDB> = arrayListOf()
    var firstCom = true
    var firstPost = true

    val apiService: ApiService
    get() = RetrofitClientInstance.getClient(BASE_URL).create(ApiService::class.java)

}