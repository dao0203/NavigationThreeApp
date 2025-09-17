package com.example.navigationthreeapp

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun App(modifier: Modifier) {
    Box(modifier = modifier) {
        HomeDisplay()
    }
}