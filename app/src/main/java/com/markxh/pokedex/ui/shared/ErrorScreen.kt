package com.markxh.pokedex.ui.shared

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ErrorScreen(errorMessage: String) {
    Text(text = "Error: $errorMessage", color = Color.Red)
}