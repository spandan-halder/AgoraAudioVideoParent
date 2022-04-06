package com.vxplore.audiovideocall.videocall

import android.app.Activity
import android.app.Application
import android.content.Intent
import com.vxplore.audiovideocall.videocall.models.AllowedResponse
import com.vxplore.audiovideocall.videocall.models.Ids
import com.vxplore.audiovideocall.videocall.presentation.activity.VideoActivity

enum class Keys{
    ChannelId,
    UserId,
    PeerId
}
class VideoBox {
    interface Callback{
        val ids: Ids?
        val appContext: Application

        fun onApproving()
        suspend fun checkAllowed(channelId: String, userId: String): AllowedResponse?
    }
    companion object{
        var callback: Callback? = null
        private var _running = false
        val running: Boolean
        get() = _running
        fun start(activity: Activity, channelId: String, userId: String){
            if(_running){
                throw Exception("Video box is already running")
            }
            _running = true
            activity.startActivity(
                Intent(activity, VideoActivity::class.java)
                    .apply {
                        putExtra(Keys.ChannelId.name,channelId)
                        putExtra(Keys.UserId.name,userId)
                    }
            )
        }
        internal fun _destroy(){
            callback = null
            _running = false
        }
    }
}