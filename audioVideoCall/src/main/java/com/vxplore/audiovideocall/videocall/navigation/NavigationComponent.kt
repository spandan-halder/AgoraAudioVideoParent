package com.vxplore.audiovideocall.videocall.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.vxplore.audiovideocall.videocall.presentation.routes.VideoRoute

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VideoCallNavigationComponent(
    navHostController: NavHostController,
    /*paddingValues: PaddingValues,*/
    data: Any? = null
) {
    AnimatedNavHost(
        navController = navHostController,
        startDestination = VideoRoute.route,
        modifier = Modifier/*.padding(paddingValues)*/
    ) {
        VideoRoute.composable(this, navHostController, data)
        /*ChatRoute.composable(
            this, navHostController
        )*/
    }
}