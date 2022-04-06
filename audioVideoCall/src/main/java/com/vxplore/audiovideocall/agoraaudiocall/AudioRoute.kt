package com.vxplore.audiovideocall.agoraaudiocall

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.vxplore.audiovideocall.agoraaudiocall.composables.AudioPage
import com.vxplore.audiovideocall.agoraaudiocall.navigation.NavRoute
import com.vxplore.audiovideocall.agoraaudiocall.navigation.Routes

object AudioRoute : NavRoute<AudioCalViewModel> {

    override val route = Routes.Audio

    @Composable
    override fun viewModel(): AudioCalViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: AudioCalViewModel, data: Any?) = AudioPage(viewModel.apply {
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



