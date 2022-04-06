package com.vxplore.audiovideocall.videocall.presentation.composables

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel

@Composable
fun HandleFinishActivity(viewModel: VideoViewModel) {
    val context = LocalContext.current

    if(viewModel.leave.value){
        SideEffect {
            viewModel.leaveChannel()
            (context as Activity).finish()
        }
    }
}