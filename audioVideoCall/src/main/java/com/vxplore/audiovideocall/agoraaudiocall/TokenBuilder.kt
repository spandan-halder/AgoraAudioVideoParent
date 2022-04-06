package com.vxplore.audiovideocall.agoraaudiocall

import com.vxplore.audiovideocall.common.tokener.media.RtcTokenBuilder

interface TokenBuilder {
    fun buildTokenWithUid(
        appId: String,
        appCertificate: String,
        channelName: String,
        uid: Int,
        role: RtcTokenBuilder.Role,
        privilegeTs: Int
    ): String
}