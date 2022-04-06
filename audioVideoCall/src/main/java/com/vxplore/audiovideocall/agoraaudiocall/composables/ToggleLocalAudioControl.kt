package com.vxplore.audiovideocall.agoraaudiocall.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.MicOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vxplore.audiovideocall.agoraaudiocall.AudioCalViewModel


@Composable
fun ToggleLocalAudioControl(viewModel: AudioCalViewModel) {
    FloatingActionButton(
        modifier = Modifier.padding(horizontal = 4.dp),
        onClick = {
            viewModel.callToggleLocal()
        },
        backgroundColor = Color.White
    ) {
        Icon(
            imageVector = if(viewModel.localAudioState.value) Icons.Outlined.Mic else Icons.Outlined.MicOff,
            contentDescription = "Toggle Local Audio Mute",
            tint = Color.Black
        )
    }
}