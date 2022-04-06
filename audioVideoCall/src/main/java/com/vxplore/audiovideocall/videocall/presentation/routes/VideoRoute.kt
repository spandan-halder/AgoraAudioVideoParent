package com.vxplore.audiovideocall.videocall.presentation.routes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.vxplore.audiovideocall.videocall.presentation.viewmodels.VideoViewModel
import com.vxplore.audiovideocall.videocall.navigation.NavRoute
import com.vxplore.audiovideocall.videocall.navigation.Routes
import com.vxplore.audiovideocall.videocall.presentation.screens.VideoPage

object VideoRoute : NavRoute<VideoViewModel> {

    override val route = Routes.Video

    @Composable
    override fun viewModel(): VideoViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: VideoViewModel, data: Any?) = VideoPage(viewModel.apply {
        setData(data)
    })

    override fun getArguments(): List<NamedNavArgument> {
        return listOf(
            navArgument("channelId") { type = NavType.StringType },
            navArgument("userId") { type = NavType.StringType },
            navArgument("peerId") { type = NavType.StringType }
        )
    }
}