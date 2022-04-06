package com.vxplore.audiovideocall.agoraaudiocall

import com.vxplore.audiovideocall.agoraaudiocall.model.AgoraAppCredential
import com.vxplore.audiovideocall.agoraaudiocall.model.MeetTimings

interface Credential {
    val userUid: Int
    val peerUid: Int
    suspend fun getChannelId(): String
    suspend fun meetTimings(): MeetTimings
    suspend fun getAgoraAppCredential(): AgoraAppCredential
}