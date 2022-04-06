package com.vxplore.audiovideocall.videocall.presentation.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel
import com.vxplore.audiovideocall.videocall.models.Layout

fun BoxScope.getRemoteModifier(
    viewModel: VideoViewModel,
    configuration: Configuration
): Modifier {
    return when(viewModel.layout.value){
        Layout.SPLIT -> Modifier
            .fillMaxWidth(if(configuration.orientation== Configuration.ORIENTATION_LANDSCAPE) 0.5f else 1f)
            .fillMaxHeight(if(configuration.orientation== Configuration.ORIENTATION_LANDSCAPE) 1f else 0.5f)
            .align(
                if(configuration.orientation==Configuration.ORIENTATION_LANDSCAPE)
                {
                    Alignment.CenterEnd
                }
                else{
                    Alignment.BottomCenter
                }
            )
        Layout.REMOTE_SMALL -> Modifier
            .fillMaxWidth(0.45f)
            .fillMaxHeight(0.4f)
            .align(Alignment.TopEnd)
            .zIndex(1f)
        Layout.LOCAL_SMALL -> Modifier
            .fillMaxSize()
            .zIndex(0f)
    }
}