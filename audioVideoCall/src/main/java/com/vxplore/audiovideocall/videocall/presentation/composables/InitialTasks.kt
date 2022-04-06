package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.runtime.Composable
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel
import com.vxplore.audiovideocall.videocall.common.ObserveLifecycleEvents

@Composable
fun InitialTasks(viewModel: VideoViewModel) {
    KeepScreenOn()
    ObserveLifecycleEvents(viewModel)
}