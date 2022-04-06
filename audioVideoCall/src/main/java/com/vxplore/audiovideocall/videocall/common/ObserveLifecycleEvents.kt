package com.vxplore.audiovideocall.videocall.common

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun ObserveLifecycleEvents(viewModel: LifeCycleAwareDelegate) {
    Log.d("fsdsfsfsfsf","ffsfsf")
    val lifecycleOwner =LocalLifecycleOwner.current
    val context = LocalContext.current
    DisposableEffect(lifecycleOwner) {
        Log.d("fsdsfsfsfsf","ffsfsf1")
        val observer = LifecycleEventObserver { source, event ->
            when(event){
                Lifecycle.Event.ON_CREATE -> viewModel.onCreate()
                Lifecycle.Event.ON_START -> viewModel.onStart()
                Lifecycle.Event.ON_RESUME -> viewModel.onResume()
                Lifecycle.Event.ON_PAUSE -> viewModel.onPause((context as Activity).isChangingConfigurations)
                Lifecycle.Event.ON_STOP -> viewModel.onStop()
                Lifecycle.Event.ON_DESTROY -> viewModel.onDestroy((context as Activity).isChangingConfigurations)
                Lifecycle.Event.ON_ANY -> viewModel.onAnyLifecycleEvent(event)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}