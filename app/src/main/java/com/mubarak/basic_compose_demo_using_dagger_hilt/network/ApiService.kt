package com.mubarak.basic_compose_demo_using_dagger_hilt.network

import com.mubarak.basic_compose_demo_using_dagger_hilt.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<Post>


}