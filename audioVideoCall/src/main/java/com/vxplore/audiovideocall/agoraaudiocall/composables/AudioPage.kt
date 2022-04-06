package com.vxplore.audiovideocall.agoraaudiocall.composables

import androidx.compose.runtime.Composable
import com.vxplore.audiovideocall.agoraaudiocall.AudioCalViewModel

@Composable
fun AudioPage(viewModel: AudioCalViewModel) {
    HandleFinish(viewModel)
    UIContent(viewModel)
}


