package com.example.unacademy.network

import com.example.leadiify.network.Api
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient2 {
    private val client = OkHttpClient.Builder().build()
    fun getInstance():Retrofit{
        return  Retrofit.Builder()
            .baseUrl("https://d872-49-36-189-216.ngrok-free.app")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    fun init(): Api
    {
        return getInstance().create(Api::class.java)
    }


}