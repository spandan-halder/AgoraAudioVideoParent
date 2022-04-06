package com.vxplore.audiovideocall.agoraaudiocall.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vxplore.audiovideocall.agoraaudiocall.toTimeString
import com.vxplore.audiovideocall.agoraaudiocall.AudioCalViewModel

@Composable
fun TimeStatus(viewModel: AudioCalViewModel) {
    Row(

    ){
        Text(
            viewModel.elapsed.value.toTimeString,
            color = Color.White
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            viewModel.left.value.toTimeString,
            color = Color.Red
        )
    }
}