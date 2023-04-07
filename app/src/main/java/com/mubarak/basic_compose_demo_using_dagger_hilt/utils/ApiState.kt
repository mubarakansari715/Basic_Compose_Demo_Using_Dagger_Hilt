package com.mubarak.basic_compose_demo_using_dagger_hilt.utils

sealed class ApiState <out T> {

    class Success<T>(val data: T) : ApiState<T>()
    class Failure(val msg: Throwable) : ApiState<Nothing>()

    object Loading : ApiState<Nothing>()
    object Empty : ApiState<Nothing>()
}

sealed class ApiStateList <out T> {

    class Success<T>(val data: List<T>) : ApiStateList<T>()
    class Failure(val msg: Throwable) : ApiStateList<Nothing>()

    object Loading : ApiStateList<Nothing>()
    object Empty : ApiStateList<Nothing>()
}