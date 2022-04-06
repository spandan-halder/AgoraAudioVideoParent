package com.vxplore.audiovideocall.videocall.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel
import io.agora.rtc.Constants
import io.agora.rtc.video.VideoCanvas

@Composable
fun LocalTextureView(viewModel: VideoViewModel) {
    val context = LocalContext.current
    AndroidView(
        factory = {
            viewModel.textureView(context)
                .apply {
                    viewModel.mEngine
                        .setupLocalVideo(
                            VideoCanvas(
                                this,
                                Constants.RENDER_MODE_HIDDEN,
                                viewModel.myUid
                            )
                        )
                }
        },
        modifier = Modifier.fillMaxSize()
    )
}