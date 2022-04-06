package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel
import com.vxplore.audiovideocall.videocall.models.LocalAudioState

@Composable
fun BoxScope.LocalAudioStateIcon(viewModel: VideoViewModel) {
    Icon(
        imageVector = if(viewModel.localAudioState.value!= LocalAudioState.MUTE) Icons.Filled.Mic else Icons.Filled.MicOff,
        contentDescription = "Local Audio State",
        modifier = Modifier
            .padding(6.dp)
            .zIndex(4f)
            .clip(CircleShape)
            .background(Color.Black.copy(0.1f))
            .align(Alignment.TopStart)
            .padding(6.dp),
        tint = Color.White
    )
}