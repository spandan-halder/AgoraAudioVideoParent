package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel

@Composable
fun VideoAndUserControls(
    viewModel: VideoViewModel
) {
    Box(
        Modifier
            .fillMaxSize()
            .clickable {
                viewModel.toggleMainControl()
            }
    ) {
        Videos(viewModel)
        UserControls(viewModel)
    }
}