package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vxplore.audiovideocall.videocall.presentation.Style

@Composable
fun BoxScope.PreparingVideoCall() {
    Column(
        modifier = Modifier.align(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CircularProgressIndicator(
            modifier = Modifier
                .size(Style.loaderSize.dp),
            color = Style.loaderColor,
            strokeWidth = 2.dp
        )
        Spacer(
            modifier = Modifier.height(Style.allowanceLoaderGap.dp)
        )
        Text(
            Style.preparingText,
            color = Style.textColor
        )
    }
}