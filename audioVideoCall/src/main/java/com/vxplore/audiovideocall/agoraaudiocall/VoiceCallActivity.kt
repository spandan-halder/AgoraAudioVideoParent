package com.vxplore.audiovideocall.agoraaudiocall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vxplore.audiovideocall.R
import com.vxplore.audiovideocall.agoraaudiocall.navigation.NavigationComponent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VoiceCallActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SoundBox.initialize(
            this,
            R.raw.call_join,
            R.raw.call_leave,
            R.raw.call_3,
            R.raw.call_4,
            R.raw.call_5,
            R.raw.call_6,
        )
        hideSystemBars()
        setContent {
            val systemUiController = rememberSystemUiController()
            navController = rememberAnimatedNavController()
            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = Color.Black,
                    darkIcons = false
                )
            }
            NavigationComponent(navController)
        }
    }

    private fun hideSystemBars() {
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView) ?: return
        // Configure the behavior of the hidden system bars
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // Hide both the status bar and the navigation bar
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }

    override fun onDestroy() {
        SoundBox.destroy()
        super.onDestroy()
    }
}
