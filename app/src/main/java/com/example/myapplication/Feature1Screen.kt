package com.example.myapplication

import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Feature1Screen(viewModel: Feature1ViewModel = viewModel()) {
    val data by viewModel.specialData.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(text = data?.name ?: "Loading...")
    }
}
