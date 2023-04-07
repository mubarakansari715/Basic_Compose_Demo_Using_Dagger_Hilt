package com.mubarak.basic_compose_demo_using_dagger_hilt

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.mubarak.basic_compose_demo_using_dagger_hilt.ui.theme.Basic_compose_demo_using_dagger_hiltTheme
import com.mubarak.basic_compose_demo_using_dagger_hilt.utils.ApiState
import com.mubarak.basic_compose_demo_using_dagger_hilt.utils.ShowLoader
import com.mubarak.basic_compose_demo_using_dagger_hilt.viewmodel.PostViewModel
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
fun showToast(msg: String) {
    val context = LocalContext.current
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

@Composable
fun GetAPIResponse(postViewModel: PostViewModel) {

    when (val getData = postViewModel.postResponse.value) {
        is ApiState.Loading -> {
            ShowLoader(isLoading = true)
        }
        is ApiState.Failure -> {
            showToast(getData.msg.toString())
            ShowLoader(isLoading = false)
        }
        is ApiState.Empty -> {
            ShowLoader(isLoading = false)
        }

        is ApiState.Success<Post> -> {
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

    Text(text = post.body)
}