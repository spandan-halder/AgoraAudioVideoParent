package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel

@Composable
fun BoxScope.MainControl(viewModel: VideoViewModel) {
    val offsetY = animateFloatAsState(
        targetValue = viewModel.mainControlOffsetY.value,
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearOutSlowInEasing
        )
    )
    LaunchedEffect(key1 = viewModel.mainControlOffsetY.value){
        viewModel.countDownMainControlStarted()
    }
    Row(
        modifier = Modifier
            .offset(y = offsetY.value.dp)
            .fillMaxWidth()
            .height(150.dp)
            .align(Alignment.BottomCenter)
            .padding(24.dp)
            .zIndex(3f),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        CallEndControl(viewModel)
        RemoteAudioControl(viewModel)
        LocalAudioControl(viewModel)
        CameraSwitchControl(viewModel)
        LocalVideoControl(viewModel)
    }
}