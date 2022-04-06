package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.runtime.Composable
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel

@Composable
fun CallScreen(viewModel: VideoViewModel) {
    if(!viewModel.leave.value){
        VideoAndUserControls(viewModel)
    }
    else{
        MeetingEnd()
    }
}