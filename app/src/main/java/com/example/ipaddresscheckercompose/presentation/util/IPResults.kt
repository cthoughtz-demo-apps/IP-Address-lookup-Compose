package com.example.ipaddresscheckercompose.presentation.util

sealed class IPResults<out T> {

    data object Loading: IPResults<Nothing>()
    data class Success<T>(val data: T): IPResults<T>()
    data class Failure<T>(val errorMessage: String): IPResults<T>()
    data object None: IPResults<Nothing>()
}