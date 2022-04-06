package com.vxplore.audiovideocall.agoraaudiocall

import android.app.Activity
import android.content.Intent

class CallBox {
    companion object{
        private var userId = ""
        private var peerId = ""
        private var startTime = 0L
        private var totalTime = 0L
        private var channelId = ""
        fun start(
            userId: String,
            peerId: String,
            channelId: String,
            activity: Activity,
            startTime: Long,
            totalTime: Long
        ){
            Companion.userId = userId
            Companion.peerId = peerId
            Companion.channelId = channelId
            Companion.startTime = startTime
            Companion.totalTime = totalTime
            activity.startActivity(Intent(activity, VoiceCallActivity::class.java))
        }

        fun getUserId(): String {
            return userId
        }

        fun getPeerId(): String {
            return peerId
        }

        fun getChannelId(): String {
            return channelId
        }

        fun destroy(){
            userId = ""
            peerId = ""
            channelId = ""
        }

        fun getStartTime(): Long {
            return startTime
        }

        fun getTotalTime(): Long {
            return totalTime
        }
    }
}