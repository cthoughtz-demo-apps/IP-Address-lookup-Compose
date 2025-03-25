package com.example.ipaddresscheckercompose.data.neteworking

import com.example.ipaddresscheckercompose.presentation.util.IPResults

sealed class NetworkResult<T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Failure<T>(val errorMessage: String): NetworkResult<T>()
}

fun <T>NetworkResult<T>.toResult() = when(this) {
    is NetworkResult.Failure -> IPResults.Failure(this.errorMessage)
    is NetworkResult.Success -> IPResults.Success(this.data)
}