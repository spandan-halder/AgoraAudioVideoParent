package com.vxplore.audiovideocall.common.tokener

import com.vxplore.audiovideocall.agoraaudiocall.TokenBuilder
import com.vxplore.audiovideocall.agoraaudiocall.Credential
import com.vxplore.audiovideocall.common.tokener.media.RtcTokenBuilder
import javax.inject.Inject


class TokenerImpl @Inject constructor(
    private val credential: Credential,
    private val tokenBuilder: TokenBuilder
): Tokener {
    override suspend fun new(): String {
        ////////////////////////////////////
        val agoraAppCredential = credential.getAgoraAppCredential()
        /////////////////////////////////////
        val appId = agoraAppCredential.appId
        val appCertificate = agoraAppCredential.appCertificate
        /////////////////////////////////////
        val meetTimings = credential.meetTimings()
        /////////////////////////////////////
        val startTime = meetTimings.startTimeMillis
        val timeSpan = meetTimings.timeSpanMillis
        /////////////////////////////////////
        val timestamp = ((startTime  + timeSpan)/ 1000).toInt()
        /////////////////////////////////////
        val channelId = credential.getChannelId()
        /////////////////////////////////////
        val uid = credential.userUid
        /////////////////////////////////////
        val token = tokenBuilder
            .buildTokenWithUid(
                appId,
                appCertificate,
                channelId,
                uid,
                RtcTokenBuilder.Role.Role_Publisher,
                timestamp
            )
        return token
    }

}