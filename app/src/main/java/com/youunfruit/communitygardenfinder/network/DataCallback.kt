package com.youunfruit.communitygardenfinder.network

interface DataCallback<T> {
    fun onSuccess(data: T)
    fun onFailure(errorMessage: String)
}