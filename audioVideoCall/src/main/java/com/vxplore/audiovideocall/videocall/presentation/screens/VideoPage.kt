package com.vxplore.audiovideocall.videocall.presentation.screens

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.vxplore.audiovideocall.videocall.presentation.composables.InitialTasks
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel
import com.vxplore.audiovideocall.videocall.presentation.composables.UserInterface

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VideoPage(
    viewModel: VideoViewModel
) {
    InitialTasks(viewModel)
    UserInterface(viewModel)
}