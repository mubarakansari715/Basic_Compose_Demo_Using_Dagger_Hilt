package com.mubarak.basic_compose_demo_using_dagger_hilt.utils

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShowToast(msg: String) {
    val context = LocalContext.current
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}