package com.mubarak.basic_compose_demo_using_dagger_hilt.repository

import com.mubarak.basic_compose_demo_using_dagger_hilt.Post
import com.mubarak.basic_compose_demo_using_dagger_hilt.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostRepository @Inject constructor(val apiService: ApiService) {

    suspend fun getPostData(): Flow<List<Post>> = flow {
        emit(apiService.getPosts())
    }
}