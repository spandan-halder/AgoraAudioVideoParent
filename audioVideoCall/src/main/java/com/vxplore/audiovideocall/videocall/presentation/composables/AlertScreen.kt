package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vxplore.audiovideocall.videocall.presentation.Style

@Composable
fun BoxScope.AlertScreen(requester: () -> Unit) {
    Column(
        modifier = Modifier.align(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            Style.permissionRequiredText,
            color = Style.textColor
        )
        Button(
            onClick = {
                requester()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Style.buttonColor,
                contentColor = Style.textColor
            )
        ) {
            Text(
                Style.allowText
            )
        }
    }
}