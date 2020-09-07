package com.example.recycleview.manager.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {

    private var retrofit: Retrofit? = null

    fun getClient(baseURL: String) : Retrofit{
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    /*public val client: ApiService by lazy{
        val client = OkHttpClient.Builder().build()

        Retrofit.Builder()
            //.baseUrl(BuildConfig.MY_URL)
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build().create(ApiService::class.java)


    }*/






}