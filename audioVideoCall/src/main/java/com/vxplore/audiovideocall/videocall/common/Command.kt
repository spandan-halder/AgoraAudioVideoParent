package com.vxplore.audiovideocall.videocall.common

data class Command(
    val action: Action,
    val target: Any?=null
)

enum class Action() {
    GO_TO_PAGE,
    ALLOWED,
    NOT_ENOUGH_TIME,
    NOT_ALLOWED,
    LOADING,
    OFFLINE,
    SUCCESS,
    VALIDATING,
    FAILED,
    ONLINE,
    RETRYING,
    DONE,
}