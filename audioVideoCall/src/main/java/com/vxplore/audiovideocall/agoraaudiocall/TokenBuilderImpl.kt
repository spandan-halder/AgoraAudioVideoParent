package com.vxplore.audiovideocall.agoraaudiocall

import com.vxplore.audiovideocall.common.tokener.media.RtcTokenBuilder

class TokenBuilderImpl: TokenBuilder {
    override fun buildTokenWithUid(
        appId: String,
        appCertificate: String,
        channelName: String,
        uid: Int,
        role: RtcTokenBuilder.Role,
        privilegeTs: Int
    ): String {
        val tokenBuilder = RtcTokenBuilder()
        val token = tokenBuilder
            .buildTokenWithUid(
                appId,
                appCertificate,
                channelName,
                uid,
                role,
                privilegeTs)
        return token
    }
}