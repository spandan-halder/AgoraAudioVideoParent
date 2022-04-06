package com.vxplore.audiovideocall.agoraaudiocall.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.vxplore.audiovideocall.agoraaudiocall.AudioCalViewModel

@Composable
fun StartAgoraCalling(viewModel: AudioCalViewModel) {
    LaunchedEffect(Unit){
        viewModel.start()
    }
}