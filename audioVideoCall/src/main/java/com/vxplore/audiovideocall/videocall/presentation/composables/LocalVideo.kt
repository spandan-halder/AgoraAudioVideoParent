package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel
import com.vxplore.audiovideocall.videocall.models.Layout
import com.vxplore.audiovideocall.videocall.models.LocalVideoState


@Composable
fun BoxScope.LocalVideo(
    viewModel: VideoViewModel
) {
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
        modifier = getLocalModifier(viewModel,configuration)
            .background(Color.Black)
            .offset((offsetX / d.density).dp, (offsetY / d.density).dp)
            .pointerInput(viewModel.layout.value) {
                detectDragGestures { change, dragAmount ->
                    if (viewModel.layout.value == Layout.LOCAL_SMALL) {
                        change.consumeAllChanges()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                }
            }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ){
            if (viewModel.localVideoState.value == LocalVideoState.ENABLED) {
                LocalTextureView(viewModel)

            }
            else{
                VideoOff()
            }

            LocalAudioStateIcon(viewModel)
            Column(
                modifier = Modifier.align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                LocalName(viewModel)
            }

        }
    }
}