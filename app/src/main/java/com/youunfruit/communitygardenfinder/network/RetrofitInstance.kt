package com.youunfruit.communitygardenfinder.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://ab7e-84-186-202-50.ngrok-free.app/"

    val api: GardenApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GardenApi::class.java)
    }
}