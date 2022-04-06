package com.vxplore.audiovideocall.videocall

import com.vxplore.audiovideocall.common.tokener.media.RtcTokenBuilder
import com.vxplore.audiovideocall.videocall.common.Constants
import com.vxplore.audiovideocall.videocall.models.CallTime
import com.vxplore.audiovideocall.videocall.models.Ids
import com.vxplore.audiovideocall.videocall.utilities.Metar

interface Tokener {
    fun new(ids: Ids, callTime: CallTime): String
}
object TokenerImpl: Tokener {
    override fun new(ids: Ids, callTime: CallTime): String{
        val tokenBuilder = RtcTokenBuilder()
        val appId = Metar[Constants.APP_ID]
        val appCertificate = Metar[Constants.APP_CERTIFICATE]
        val timestamp = ((callTime.startTime  + callTime.timeSpan)/ 1000).toInt()
        val token = tokenBuilder
            .buildTokenWithUid(
                appId,
                appCertificate,
                ids.channelId,
                0,
                RtcTokenBuilder.Role.Role_Publisher,
                timestamp)
        return token
    }
}