package com.vxplore.audiovideocall.agoraaudiocall.composables

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import com.vxplore.audiovideocall.agoraaudiocall.AudioCalViewModel

@Composable
fun UIContent(viewModel: AudioCalViewModel) {
    BackHandler(true) {
        viewModel.onBackPressed()
    }
    UIRequirePermissions(
        permissions = viewModel.permissions,
        onPermissionGranted = {
            StartAgoraCalling(viewModel)
            CallingContent(viewModel)
        },
        onPermissionDenied = {
            AllowContent(it)
        }
    )
}






