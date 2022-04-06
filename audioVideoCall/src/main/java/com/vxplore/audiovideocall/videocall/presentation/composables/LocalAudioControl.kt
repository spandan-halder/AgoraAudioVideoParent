package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel
import com.vxplore.audiovideocall.videocall.models.LocalAudioState

@Composable
fun LocalAudioControl(viewModel: VideoViewModel) {
    FloatingActionButton(
        onClick = {
            viewModel.switchLocalAudioState()
        },
        backgroundColor = Color.White,
    ) {
        Icon(
            imageVector = when(viewModel.localAudioState.value){
                LocalAudioState.MUTE -> Icons.Filled.MicOff
                LocalAudioState.UNMUTE -> Icons.Filled.Mic
            },
            tint = Color.Black,
            contentDescription = "Call End"
        )
    }
}