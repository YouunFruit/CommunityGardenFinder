package com.youunfruit.communitygardenfinder.objects

import com.google.gson.annotations.SerializedName

data class Garden(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("street_name") val streetName: String?,
    @SerializedName("photo") val photo: String?,
    @SerializedName("is_public") val isPublic: Boolean,
    @SerializedName("joinable") val joinable: Boolean,
    @SerializedName("owner_id") val ownerId: Int,
    @SerializedName("tags") val tags: List<Tag>?
)
