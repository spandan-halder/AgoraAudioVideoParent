package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.runtime.Composable
import com.vxplore.audiovideocall.videocall.common.ObserveLifecycleEvents
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel

@Composable
fun InitialTasks(viewModel: VideoViewModel) {
    HandleFinishActivity(viewModel)
    KeepScreenOn()
    ObserveLifecycleEvents(viewModel)
}