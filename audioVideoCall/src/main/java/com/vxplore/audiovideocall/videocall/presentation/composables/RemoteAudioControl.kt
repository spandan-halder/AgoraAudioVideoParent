package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel
import com.vxplore.audiovideocall.videocall.models.AudioRoute

@Composable
fun RemoteAudioControl(viewModel: VideoViewModel) {
    FloatingActionButton(
        onClick = {
            viewModel.switchAudio()
        },
        backgroundColor = Color.White,
    ) {
        Icon(
            imageVector = when(viewModel.audioRoute.value){
                AudioRoute.MUTE -> Icons.Filled.VolumeMute
                AudioRoute.SPEAKER -> Icons.Filled.VolumeUp
            },
            tint = Color.Black,
            contentDescription = "Call End"
        )
    }
}