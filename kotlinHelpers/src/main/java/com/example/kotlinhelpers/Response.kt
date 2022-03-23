package com.example.kotlinhelpers

sealed class Response<T> {
    class Success<T>(val value: T) : Response<T>()
    class Error<T>(val message: String) : Response<T>()
}