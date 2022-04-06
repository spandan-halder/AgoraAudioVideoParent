package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vxplore.audiovideocall.videocall.presentation.Style
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel

@Composable
fun BoxScope.RemoteName(viewModel: VideoViewModel) {
    Text(
        viewModel.remoteName.value,
        color = Style.textColor,
        modifier = Modifier
            .padding(6.dp)
            .align(Alignment.BottomCenter)
            .clip(CircleShape)
            .background(Color.Black.copy(0.1f))
            .padding(2.dp)
    )
}