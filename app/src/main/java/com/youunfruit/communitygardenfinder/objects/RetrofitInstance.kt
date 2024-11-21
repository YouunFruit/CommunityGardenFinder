package com.youunfruit.communitygardenfinder.objects

import com.youunfruit.communitygardenfinder.GardenApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://your-api-url.com/"

    val api: GardenApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GardenApi::class.java)
    }
}