package com.mubarak.basic_compose_demo_using_dagger_hilt.post.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mubarak.basic_compose_demo_using_dagger_hilt.post.model.Post
import com.mubarak.basic_compose_demo_using_dagger_hilt.ui.theme.Basic_compose_demo_using_dagger_hiltTheme
import com.mubarak.basic_compose_demo_using_dagger_hilt.utils.ApiState
import com.mubarak.basic_compose_demo_using_dagger_hilt.utils.ShowLoader
import com.mubarak.basic_compose_demo_using_dagger_hilt.utils.ShowToast
import com.mubarak.basic_compose_demo_using_dagger_hilt.post.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Basic_compose_demo_using_dagger_hiltTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Greeting("Android")
                    GetAPIResponse(viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Basic_compose_demo_using_dagger_hiltTheme {
        Greeting("Android")
    }
}

@Composable
fun GetAPIResponse(postViewModel: PostViewModel) {

    when (val getData = postViewModel.postResponse.value) {
        is ApiState.Loading -> {
            ShowLoader(isLoading = true)
        }
        is ApiState.Failure -> {
            ShowToast(getData.msg.toString())
            ShowLoader(isLoading = false)
        }
        is ApiState.Empty -> {
            ShowLoader(isLoading = false)
        }

        is ApiState.Success<List<Post>> -> {
            ShowLoader(isLoading = false)
            Log.e("@@@TAG", "GetAPIResponse: ${getData.data}")
            LazyColumn {
                items(getData.data) { response ->
                    EachRowItem(post = response)
                }
            }
        }

    }
}

@Composable
fun EachRowItem(post: Post) {

    Card(
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        backgroundColor = Color.White,
        elevation = 2.dp,
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()

        ) {
            Text(text = post.body)
        }

    }
}