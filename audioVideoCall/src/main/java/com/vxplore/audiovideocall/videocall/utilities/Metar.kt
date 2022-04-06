package com.vxplore.audiovideocall.videocall.utilities

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager

object Metar {
    private var app: Application? = null
    operator fun get(key: String): String {
        return try {
            val ai = (app as Context)
                .packageManager
                .getApplicationInfo(
                    (app as Context)
                        .packageName,
                    PackageManager.GET_META_DATA
                )
            val bundle = ai.metaData
            bundle.getString(key)?:""
        } catch (e: PackageManager.NameNotFoundException) {
            ""
        }
    }
    fun initialize(app: Application) {
        Metar.app = app
    }

    fun destroy() {
        app = null
    }
}