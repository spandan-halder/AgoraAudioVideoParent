package com.vxplore.audiovideocall.agoraaudiocall.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vxplore.audiovideocall.agoraaudiocall.AudioCalViewModel

@Composable
fun UserControls(viewModel: AudioCalViewModel) {
    Row(
        modifier = Modifier.wrapContentWidth().padding(24.dp)
    ){
        CallEndControl(viewModel)
        ToggleLocalAudioControl(viewModel)
        ToggleRemoteAudioControl(viewModel)
    }
}





