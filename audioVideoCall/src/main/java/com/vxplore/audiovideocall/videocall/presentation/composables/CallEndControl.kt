package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel

@Composable
fun CallEndControl(viewModel: VideoViewModel) {
    FloatingActionButton(
        onClick = {
            viewModel.endCall()
        },
        backgroundColor = Color.Red,
    ) {
        Icon(
            imageVector = Icons.Filled.Call,
            tint = Color.White,
            contentDescription = "Call End"
        )
    }
}