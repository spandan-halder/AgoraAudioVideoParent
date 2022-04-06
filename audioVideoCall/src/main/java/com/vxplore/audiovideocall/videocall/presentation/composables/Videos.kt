package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel
import com.vxplore.audiovideocall.videocall.models.Layout

@Composable
fun BoxScope.Videos(
    viewModel: VideoViewModel
) {
    if(viewModel.layout.value== Layout.REMOTE_SMALL){
        LocalVideo(viewModel)
        RemoteVideo(viewModel)
    }
    else{
        RemoteVideo(viewModel)
        LocalVideo(viewModel)
    }
}