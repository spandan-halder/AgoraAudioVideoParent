package com.vxplore.audiovideocall.agoraaudiocall.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vxplore.audiovideocall.agoraaudiocall.AudioCalViewModel


@Composable
fun CallEndControl(viewModel: AudioCalViewModel) {
    FloatingActionButton(
        modifier = Modifier.padding(horizontal = 4.dp),
        onClick = {
            viewModel.callEnd()
        },
        backgroundColor = Color.Red
    ) {
        Icon(
            imageVector = Icons.Filled.Call,
            contentDescription = "Call End",
            tint = Color.White
        )
    }
}