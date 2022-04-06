package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vxplore.audiovideocall.videocall.presentation.Style

@Composable
fun MeetingEnd() {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            "Meeting ended",
            color = Style.textColor,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}