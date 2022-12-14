package com.vxplore.audiovideocall.agoraaudiocall.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vxplore.audiovideocall.agoraaudiocall.AudioCalViewModel

@Composable
fun ToggleRemoteAudioControl(viewModel: AudioCalViewModel) {
    FloatingActionButton(
        modifier = Modifier.padding(horizontal = 4.dp),
        onClick = {
            viewModel.toggleRemoteAudio()
        },
        backgroundColor = Color.White
    ) {
        Icon(
            imageVector = if(viewModel.remoteAudioState.value) Icons.Filled.VolumeUp else Icons.Filled.VolumeMute,
            contentDescription = "Toggle Remote Audio",
            tint = Color.Black
        )
    }
}