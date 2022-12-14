package com.vxplore.audiovideocall.agoraaudiocall

interface AgoraAudioCallback{
    fun onJoinChannel()
    fun onTick(elapsed: Int, left: Int, totalAllowedSeconds: Int)
    fun onLocalAudioStateChanged(b: Boolean)
    fun onTokenExpired()
    fun onSamples(arr: ByteArray)
    fun onRemoteAudioMute(muted: Boolean)
    fun onUserJoinStatus(uid: Int, online: Boolean)
}