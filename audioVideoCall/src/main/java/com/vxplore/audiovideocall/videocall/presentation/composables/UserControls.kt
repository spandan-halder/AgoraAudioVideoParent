package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.vxplore.audiovideocall.videocall.utilities.toTimeString

@Composable
fun BoxScope.UserControls(
    viewModel: VideoViewModel
) {
    LayoutControl(viewModel)
    Row(
        modifier = Modifier.align(Alignment.BottomCenter)
            .padding(32.dp)
            .clip(CircleShape)
            .background(Color.Black.copy(0.1f))
            .padding(2.dp)
    ) {
        Text(
            viewModel.timeSeconds.value.toTimeString,
            color = Style.textColor,
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            "-"+viewModel.leftTimeSeconds.value.toTimeString,
            color = Color.Red,
        )
    }

    MainControl(viewModel)
}