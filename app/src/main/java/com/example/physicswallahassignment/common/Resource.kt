package com.example.physicswallahassignment.common

sealed class Resource<T>(val data: T? = null, val error: String? = null) {
    class Loading<T>() : Resource<T>()

    class Success<T>(data: T?) : Resource<T>(data = data)

    class Error<T>(error: String?) : Resource<T>(error = error)
}