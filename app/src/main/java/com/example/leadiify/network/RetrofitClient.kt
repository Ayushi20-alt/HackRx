package com.example.unacademy.network

import com.example.leadiify.network.Api
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val client = OkHttpClient.Builder().build()
    fun getInstance():Retrofit{
        return  Retrofit.Builder()
            .baseUrl("http://10.10.220.36:9999/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    fun init(): Api
    {
        return getInstance().create(Api::class.java)
    }
}