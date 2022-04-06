package com.vxplore.audiovideocall.videocall.presentation.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.vxplore.audiovideocall.videocall.models.Layout
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel

fun BoxScope.getRemoteModifier(
    viewModel: VideoViewModel,
    configuration: Configuration
): Modifier {
    val w = configuration.screenWidthDp
    val h = configuration.screenHeightDp
    val s = w.coerceAtMost(h)*0.25f
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
            .width(s.dp)
            .aspectRatio(9/16f)
            .align(Alignment.TopEnd)
            .zIndex(1f)
        Layout.LOCAL_SMALL -> Modifier
            .fillMaxSize()
            .zIndex(0f)
    }
}