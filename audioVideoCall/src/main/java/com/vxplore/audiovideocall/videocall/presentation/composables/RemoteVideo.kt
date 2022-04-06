package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel
import com.vxplore.audiovideocall.videocall.models.Layout

@Composable
fun BoxScope.RemoteVideo(viewModel: VideoViewModel) {
    if(viewModel.remoteUserSession.value.uid<1){
        return
    }
    var offsetX by remember {
        mutableStateOf(0f)
    }
    var offsetY by remember {
        mutableStateOf(0f)
    }
    LaunchedEffect(key1 = viewModel.layout.value){
        offsetX = 0f
        offsetY = 0f
    }
    val d = LocalDensity.current
    val configuration = LocalConfiguration.current
    Card(
        modifier = getRemoteModifier(viewModel,configuration)
            .offset((offsetX / d.density).dp, (offsetY / d.density).dp)
            .pointerInput(viewModel.layout.value) {
                detectDragGestures { change, dragAmount ->
                    if (viewModel.layout.value == Layout.REMOTE_SMALL) {
                        change.consumeAllChanges()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                }
            }

    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
        ){
            if (viewModel.remoteVideoEnabled.value) {
                RemoteTextureVideo(viewModel)
            }
            else{
                VideoOff()
            }

            RemoteAudioState(viewModel)
            RemoteName(viewModel)
        }
    }
}