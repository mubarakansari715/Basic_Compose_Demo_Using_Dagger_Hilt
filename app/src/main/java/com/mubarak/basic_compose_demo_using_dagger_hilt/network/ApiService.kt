package com.mubarak.basic_compose_demo_using_dagger_hilt.network

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<Post>


}