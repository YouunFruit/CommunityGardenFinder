package com.youunfruit.communitygardenfinder.network

import com.youunfruit.communitygardenfinder.objects.Garden
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GardenApi {
    @GET("gardens/{id}")
    suspend fun getGarden(@Path("id") gardenId: String): Garden

    @GET("gardens")
    suspend fun getGardens(): List<Garden>

    @POST("gardens/{id}?user_id={user_id}")
    suspend fun joinGarden()

    @GET("users/{id}/gardens")
    suspend fun getJoinedGardens(@Path("id") userId: String): List<Garden>
}