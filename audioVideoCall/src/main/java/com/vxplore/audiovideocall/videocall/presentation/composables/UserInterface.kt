package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vxplore.audiovideocall.videocall.presentation.Style
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel

@Composable
fun UserInterface(viewModel: VideoViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Style.pageBackgroundColor)
    ){
        when {
            viewModel.allowanceLoading.value -> {
                AllowanceLoadingView()
            }
            viewModel.preparingVideoCall.value -> {
                PreparingVideoCall()
            }
            viewModel.videoCallAllowed.value -> {
                VideoCallView(viewModel)
            }
        }
    }
}



