package com.vxplore.audiovideocall.agoraaudiocall

interface Metar {
    operator fun get(key: String): String
}