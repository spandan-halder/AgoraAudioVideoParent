package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel

@Composable
fun CameraSwitchControl(viewModel: VideoViewModel) {
    FloatingActionButton(
        onClick = {
            viewModel.switchCamera()
        },
        backgroundColor = Color.White,
    ) {
        Icon(
            imageVector = Icons.Filled.Cameraswitch,
            tint = Color.Black,
            contentDescription = "Switch Camera"
        )
    }
}