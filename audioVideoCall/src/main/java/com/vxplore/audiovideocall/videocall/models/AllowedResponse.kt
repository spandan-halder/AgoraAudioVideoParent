package com.vxplore.audiovideocall.videocall.models

data class AllowedResponse(
    val success: Boolean,
    val message: String,
    val startTime: Long,
    val timeSpan: Long
)