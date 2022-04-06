package com.vxplore.audiovideocall.videocall.presentation.viewmodels;

import android.Manifest
import android.app.Application
import android.content.Context
import android.util.Log
import android.view.TextureView
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vxplore.audiovideocall.videocall.Tokener
import com.vxplore.audiovideocall.videocall.VideoBox
import com.vxplore.audiovideocall.videocall.common.Action
import com.vxplore.audiovideocall.videocall.common.Command
import com.vxplore.audiovideocall.videocall.common.Constants
import com.vxplore.audiovideocall.videocall.common.LifeCycleAwareDelegate
import com.vxplore.audiovideocall.videocall.models.*
import com.vxplore.audiovideocall.videocall.navigation.RouteNavigator
import com.vxplore.audiovideocall.videocall.usecases.VideoUseCase
import com.vxplore.audiovideocall.videocall.utilities.Metar
import com.vxplore.audiovideocall.videocall.utilities.next
import dagger.hilt.android.lifecycle.HiltViewModel
import io.agora.rtc.Constants.REMOTE_AUDIO_STATE_STARTING
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
    private val videoUseCase: VideoUseCase,
    private val tokener: Tokener
) : LifeCycleAwareDelegate, ViewModel(), RouteNavigator by routeNavigator {
    private var ids: Ids? = null
    ///////////////////////////////////////////
    val timeSeconds = mutableStateOf(0)
    val leftTimeSeconds = mutableStateOf(0)
    val destroyRtcByTimestamp = mutableStateOf(0L)
    val mainControlOffsetY = mutableStateOf(0f)
    val remoteName = mutableStateOf("")
    val localName = mutableStateOf("")
    val localVideoState = mutableStateOf(LocalVideoState.ENABLED)
    val localAudioState = mutableStateOf(LocalAudioState.UNMUTE)
    val audioRoute = mutableStateOf(AudioRoute.SPEAKER)
    val layout = mutableStateOf(Layout.REMOTE_SMALL)
    val leave = mutableStateOf(false)
    val remoteVideoEnabled = mutableStateOf(true)
    private val _allowanceLoading = mutableStateOf(false)
    val allowanceLoading: MutableState<Boolean> = _allowanceLoading

    private val _preparingVideoCall = mutableStateOf(false)
    val preparingVideoCall: MutableState<Boolean> = _preparingVideoCall

    private val _videoCallAllowed = mutableStateOf(false)
    val videoCallAllowed: MutableState<Boolean> = _videoCallAllowed
    //////////////////////////////////////////////////
    val myUid: Int
    get(){
        return if(ids!!.userId>ids!!.peerId){
            1
        }else{
            2
        }
    }
    val channelName: String
        get() = ids?.channelId?:""
    val agoraAppId: String
        get() = Metar[Constants.APP_ID]
    private val appContext: Application
        get() = VideoBox.callback?.appContext?:(throw Exception("Application context not found. Pleas check the callback is set or not"))
    //////////////////////////////
    var token: String = ""
    private var time: CallTime? = null

    //////////////////////////////
    val permissions = arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA)
    ////////////////////////////////
    override fun onCreate() {
    }

    override fun onStart() {

    }

    override fun onResume() {

    }

    override fun onPause(isChangingConfiguration: Boolean) {
        if(isChangingConfiguration){
            destroyRtcByTimestamp.value = System.currentTimeMillis()
        }
    }

    override fun onStop() {

    }

    override fun onDestroy(isChangingConfiguration: Boolean) {
        if(!isChangingConfiguration){
            leaveChannel()
        }
    }

    override fun onAnyLifecycleEvent(event: Lifecycle.Event) {

    }

    override fun onCleared() {
        super.onCleared()
    }

    fun setData(data: Any?) {

    }

    private fun afterIdsGot() {
        localName.value = ids!!.userName
        remoteName.value = ids!!.peerName
        checkAllowed()
    }

    private fun checkAllowed() {
        VideoBox.callback?.onApproving()
        videoUseCase.checkAllowed(ids!!.channelId,ids!!.userId).onEach {
            when(it.action){
                Action.ALLOWED -> {
                    onAllowedFromServer(it)
                }
                Action.FAILED -> {
                    onFailedFromServer(it)
                }
                Action.NOT_ENOUGH_TIME -> {
                    _allowanceLoading.value = false
                }
                Action.NOT_ALLOWED -> {
                    _allowanceLoading.value = false
                }
                Action.LOADING -> {
                    _allowanceLoading.value = true
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun onFailedFromServer(it: Command) {
        endCall()
    }

    private suspend fun onAllowedFromServer(it: Command) {
        _allowanceLoading.value = false
        _preparingVideoCall.value = true
        time = it.target as CallTime
        prepareVideoCall()
    }

    private suspend fun prepareVideoCall() {
        token = tokener.new(ids?:return,time?:return)
        delay(500)
        _preparingVideoCall.value = false
        _videoCallAllowed.value = true
    }

    fun allowVideoCall() {
        viewModelScope.launch {
            _preparingVideoCall.value = true
            _videoCallAllowed.value = false
            delay(500)
            _preparingVideoCall.value = false
            _videoCallAllowed.value = true
        }
    }

    var joined = false
    fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
        Log.d("channelId",channel.toString())
        if(!joined){
            joined = true
            startTimer()
        }
    }

    var timerJob: Job? = null
    private fun startTimer() {
        timerJob = viewModelScope.launch {
            while(true){
                delay(1000)
                timeSeconds.value = timeSeconds.value + 1
                updateLeftTime()
            }
        }
    }

    private fun updateLeftTime() {
        val startTime = time?.startTime?:return
        val timespan = time?.timeSpan?:return
        val endTime = startTime + timespan
        val now = System.currentTimeMillis()
        val left = endTime - now
        leftTimeSeconds.value = (left/1000).toInt()
        if(leftTimeSeconds.value==0){
            leave.value = true
        }
    }

    fun onUserJoined(uid: Int, elapsed: Int) {

    }

    fun onUserOffline(uid: Int, reason: Int) {

    }

    fun endCall() {
        leave.value = true
    }

    fun switchVideoLayout() {
        layout.value = layout.value.next()
    }

    fun switchAudio() {
        showUserControl()
        audioRoute.value = audioRoute.value.next()
        when(audioRoute.value){
            AudioRoute.MUTE -> mEngine.muteAllRemoteAudioStreams(true)
            AudioRoute.SPEAKER -> mEngine.muteAllRemoteAudioStreams(false)
        }
    }

    fun switchLocalAudioState() {
        showUserControl()
        localAudioState.value = localAudioState.value.next()
        when(localAudioState.value){
            LocalAudioState.MUTE -> {
                mEngine.muteLocalAudioStream(true)
                mEngine.enableLocalAudio(false)
            }
            LocalAudioState.UNMUTE -> {
                mEngine.enableLocalAudio(true)
                mEngine.muteLocalAudioStream(false)
            }
        }
    }

    fun switchCamera() {
        showUserControl()
        mEngine.switchCamera()
    }

    fun switchLocalVideoState() {
        showUserControl()
        localVideoState.value = localVideoState.value.next()
        if(localVideoState.value== LocalVideoState.DISABLED){
            mEngine.enableLocalVideo(false)
        }
        else{
            mEngine.enableLocalVideo(true)
        }
    }

    fun onUserEnabledLocalVideo(uid: Int, enabled: Boolean) {
        if(uid!=myUid){
            remoteVideoEnabled.value = enabled
        }
    }

    val remoteAudio = mutableStateOf(true)
    fun onRemoteAudioStateChanged(uid: Int, state: Int) {
        if(uid!=myUid){
            remoteAudio.value = state == REMOTE_AUDIO_STATE_STARTING
        }
    }

    fun onUserMuteAudio(uid: Int, muted: Boolean) {
        if(uid!=myUid){
            remoteAudio.value = !muted
        }
    }

    var job: Job? = null
    fun countDownMainControlStarted() {
        job?.cancel()
        job = viewModelScope.launch {
            delay(5000)
            hideUserControl()
        }
    }

    fun showUserControl(){
        job?.cancel()
        mainControlOffsetY.value = 0f
        countDownMainControlStarted()
    }

    fun hideUserControl(){
        job?.cancel()
        mainControlOffsetY.value = 200f
    }

    fun isUserControlShown():Boolean{
        return mainControlOffsetY.value==0f
    }

    fun toggleMainControl() {
        if(isUserControlShown()){
            hideUserControl()
        }else{
            showUserControl()
        }
    }

    fun destroy() {
        RtcEngine.destroy()
        Log.d("rtc_engine_destroyed","yes")
        Metar.destroy()
        VideoBox._destroy()
    }

    fun leaveChannel() {
        mEngine.leaveChannel()
        timerJob?.cancel()
        timerJob = null
        destroy()
        VideoBox._destroy()
        viewModelScope.launch {
            leave.value = true
        }
    }

    val remoteUserSession = mutableStateOf(RemoteSession())

    val eventHandler = object : IRtcEngineEventHandler() {
        override fun onUserMuteAudio(uid: Int, muted: Boolean) {
            this@VideoViewModel.onUserMuteAudio(uid, muted)
        }
        override fun onUserEnableLocalVideo(uid: Int, enabled: Boolean) {
            this@VideoViewModel.onUserEnabledLocalVideo(uid,enabled)
        }

        override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
            this@VideoViewModel.onJoinChannelSuccess(channel,uid,elapsed)
        }

        override fun onError(err: Int) {
            super.onError(err)
        }

        override fun onUserJoined(uid: Int, elapsed: Int) {
            this@VideoViewModel.onUserJoined(uid,elapsed)
            remoteUserSession.value = RemoteSession(uid,System.currentTimeMillis())
        }

        override fun onUserOffline(uid: Int, reason: Int) {
            this@VideoViewModel.onUserOffline(uid,reason)
            remoteUserSession.value = RemoteSession()
        }
    }
    var _mEngine: RtcEngine? = null
    val mEngine: RtcEngine
    get(){
        if(_mEngine==null){
            _mEngine = RtcEngine.create(appContext, agoraAppId, eventHandler).apply {
                enableVideo()
                setChannelProfile(1)
                setClientRole(1)
                joinChannel(token, channelName, "", myUid)
            }
        }
        return _mEngine!!
    }
    /////////////////////////////////////
    fun textureView(context: Context): TextureView{
        return RtcEngine.CreateTextureView(context)
    }

    init {
        val l = leave.value
        Metar.initialize(appContext)
        ids = VideoBox.callback?.ids
        afterIdsGot()
    }
}

