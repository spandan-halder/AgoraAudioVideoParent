package com.vxplore.audiovideocall.videocall.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vxplore.audiovideocall.videocall.navigation.VideoCallNavigationComponent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class VideoActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            navController = rememberAnimatedNavController()
            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = Color.Black,
                    darkIcons = false
                )
            }
            VideoCallNavigationComponent(navController)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if(intent?.getBooleanExtra("finish",false)==true){
            finish()
        }
    }

    override fun onBackPressed() {
        finish()
    }
}