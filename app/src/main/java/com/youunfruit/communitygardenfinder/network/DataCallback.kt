package com.youunfruit.communitygardenfinder.network

interface DataCallback<T> {
    suspend fun onSuccess(data: T)
    fun onFailure(errorMessage: String)
}