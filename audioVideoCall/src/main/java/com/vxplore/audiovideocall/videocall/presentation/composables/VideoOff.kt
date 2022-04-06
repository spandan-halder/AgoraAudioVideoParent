package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VideocamOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun VideoOff() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ){
        Icon(
            imageVector = Icons.Filled.VideocamOff,
            contentDescription = "Video off",
            modifier = Modifier
                .fillMaxSize(0.25f)
                .align(Alignment.Center),
            tint = Color.Gray
        )
    }
}