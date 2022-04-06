package com.vxplore.audiovideocall.videocall.usecases

import com.vxplore.audiovideocall.videocall.VideoBox
import com.vxplore.audiovideocall.videocall.common.Action
import com.vxplore.audiovideocall.videocall.common.Command
import com.vxplore.audiovideocall.videocall.models.CallTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import javax.inject.Inject

class VideoUseCase @Inject constructor(

) {
    fun checkAllowed(channelId: String, userId: String): Flow<Command> = flow {
        emit(Command(Action.LOADING))
        val allowedResponse = VideoBox.callback?.checkAllowed(channelId,userId)
        if(allowedResponse==null){
            emit(Command(Action.FAILED))
            return@flow
        }
        if(!allowedResponse.success){
            emit(Command(Action.NOT_ALLOWED,allowedResponse.message))
            return@flow
        }
        val startTime = allowedResponse.startTime
        val startTimeLocal = DateTime(startTime, DateTimeZone.UTC).toDateTime(DateTimeZone.getDefault()).millis
        val endTime = allowedResponse.startTime + allowedResponse.timeSpan
        val endTimeLocal = DateTime(endTime, DateTimeZone.UTC).toDateTime(DateTimeZone.getDefault()).millis
        val now = System.currentTimeMillis()
        val left = endTimeLocal - now
        if(left<=5){
            emit(Command(Action.NOT_ENOUGH_TIME))
            return@flow
        }
        val time = CallTime(
            startTime = startTimeLocal,
            timeSpan = allowedResponse.timeSpan
        )
        emit(Command(Action.ALLOWED,time))
    }
}