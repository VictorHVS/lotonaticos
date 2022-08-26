package com.victorhvs.lotonaticos.domain

sealed class State<out T> {
    object Loading : State<Nothing>()
    data class Success<T>(val data: T) : State<T>()
    data class Failed<T>(val message: String = "") : State<T>()

    companion object {
        fun loading() = Loading
        fun <T> success(data: T) = Success(data)
        fun <T> failed(message: String) = Failed<T>(message)
    }
}
