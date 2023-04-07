package com.mubarak.basic_compose_demo_using_dagger_hilt.utils

sealed class ApiState <out T> {

    class Success<T>(val data: List<T>) : ApiState<T>()
    class Failure(val msg: Throwable) : ApiState<Nothing>()

    object Loading : ApiState<Nothing>()
    object Empty : ApiState<Nothing>()
}