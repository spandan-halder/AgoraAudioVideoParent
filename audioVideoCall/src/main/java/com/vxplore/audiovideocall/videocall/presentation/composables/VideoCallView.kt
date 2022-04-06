package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel

@Composable
fun BoxScope.VideoCallView(viewModel: VideoViewModel) {
    UIRequirePermissions(
        permissions = viewModel.permissions,
        onPermissionGranted = {
            CallScreen(viewModel)
        },
        onPermissionDenied = {
            AlertScreen(it)
        }
    )
}