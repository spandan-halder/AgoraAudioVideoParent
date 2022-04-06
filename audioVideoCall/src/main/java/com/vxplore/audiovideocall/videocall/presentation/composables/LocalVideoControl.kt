package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.filled.VideocamOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel
import com.vxplore.audiovideocall.videocall.models.LocalVideoState

@Composable
fun LocalVideoControl(viewModel: VideoViewModel) {
    FloatingActionButton(
        onClick = {
            viewModel.switchLocalVideoState()
        },
        backgroundColor = Color.White,
    ) {
        Icon(
            imageVector = when(viewModel.localVideoState.value){
                LocalVideoState.ENABLED -> Icons.Filled.Videocam
                LocalVideoState.DISABLED -> Icons.Filled.VideocamOff
            },
            tint = Color.Black,
            contentDescription = "Call End"
        )
    }
}