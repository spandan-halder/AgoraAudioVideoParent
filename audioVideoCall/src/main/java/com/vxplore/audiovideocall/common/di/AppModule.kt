package com.vxplore.audiovideocall.common.di

import android.content.Context
import com.vxplore.audiovideocall.videocall.navigation.MyRouteNavigator
import com.vxplore.audiovideocall.videocall.navigation.RouteNavigator
import com.vxplore.audiovideocall.agoraaudiocall.AgoraAudioCall
import com.vxplore.audiovideocall.agoraaudiocall.CredentialImpl
import com.vxplore.audiovideocall.agoraaudiocall.Metar
import com.vxplore.audiovideocall.agoraaudiocall.MetarImpl
import com.vxplore.audiovideocall.agoraaudiocall.AgoraAudioCallImpl
import com.vxplore.audiovideocall.agoraaudiocall.Credential
import com.vxplore.audiovideocall.agoraaudiocall.TokenBuilder
import com.vxplore.audiovideocall.agoraaudiocall.TokenBuilderImpl
import com.vxplore.audiovideocall.videocall.Tokener
import com.vxplore.audiovideocall.videocall.TokenerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTokener(): Tokener {
        return TokenerImpl
    }
    ////////////////////////////
    @Provides
    @Singleton
    fun provideCredential(metar: Metar): Credential {
        return CredentialImpl(metar)
    }

    @Provides
    @Singleton
    fun provideMetar(@ApplicationContext application: Context): Metar {
        return MetarImpl(application)
    }

    @Provides
    fun provideTokenBuilder(): TokenBuilder {
        return TokenBuilderImpl()
    }

    @Provides
    fun provideVideoTokener(credential: Credential, tokenBuilder: TokenBuilder): com.vxplore.audiovideocall.common.tokener.Tokener {
        return /*Static*/com.vxplore.audiovideocall.common.tokener.TokenerImpl(
            credential,
            tokenBuilder
        )
    }
}

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun bindRouteNavigator(): RouteNavigator = MyRouteNavigator()
    ///////////////////////////
    @Provides
    @ViewModelScoped
    fun videoBindRouteNavigator(): com.vxplore.audiovideocall.agoraaudiocall.navigation.RouteNavigator =
        com.vxplore.audiovideocall.agoraaudiocall.navigation.MyRouteNavigator()

    @Provides
    @ViewModelScoped
    fun provideAgoraAudioCall(
        credential: Credential,
        tokener: com.vxplore.audiovideocall.common.tokener.Tokener,
        @ApplicationContext application: Context
    ): AgoraAudioCall {
        return AgoraAudioCallImpl(credential,tokener,application)
    }
}