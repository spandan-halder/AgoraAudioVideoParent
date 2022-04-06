package com.vxplore.audiovideocall.common.tokener


interface Tokener {
    suspend fun new(): String
}