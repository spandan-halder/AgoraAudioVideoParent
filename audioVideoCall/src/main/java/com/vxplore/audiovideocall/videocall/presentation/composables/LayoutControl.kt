package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Fullscreen
import androidx.compose.material.icons.outlined.FullscreenExit
import androidx.compose.material.icons.outlined.Splitscreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel
import com.vxplore.audiovideocall.videocall.models.Layout

@Composable
fun BoxScope.LayoutControl(
    viewModel: VideoViewModel
) {
    IconButton(
        onClick = {
            viewModel.switchVideoLayout()
        },
        modifier = Modifier
            .padding(12.dp)
            .clip(CircleShape)
            .background(Color.Black.copy(0.05f))
            .align(Alignment.TopEnd)
            .zIndex(2f)
    ) {
        Icon(
            imageVector = when(viewModel.layout.value){
                Layout.SPLIT -> Icons.Outlined.Splitscreen
                Layout.REMOTE_SMALL -> Icons.Outlined.Fullscreen
                Layout.LOCAL_SMALL -> Icons.Outlined.FullscreenExit
            },
            tint = Color.White,
            contentDescription = "Layout"
        )
    }
}