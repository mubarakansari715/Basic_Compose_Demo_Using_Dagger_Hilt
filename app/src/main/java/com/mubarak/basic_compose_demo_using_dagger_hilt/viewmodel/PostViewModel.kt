package com.mubarak.basic_compose_demo_using_dagger_hilt.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mubarak.basic_compose_demo_using_dagger_hilt.Post
import com.mubarak.basic_compose_demo_using_dagger_hilt.repository.PostRepository
import com.mubarak.basic_compose_demo_using_dagger_hilt.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(val repository: PostRepository) : ViewModel() {

    val postResponse: MutableState<ApiState<Post>> = mutableStateOf(ApiState.Empty)

    init {
        getPostDataResponse()
    }

    fun getPostDataResponse() = viewModelScope.launch {

        repository.getPostData()
            .onStart {
                postResponse.value = ApiState.Loading
            }
            .catch {
                postResponse.value = ApiState.Failure(it)
            }
            .collect {
                postResponse.value = ApiState.Success(it)
            }
    }
}