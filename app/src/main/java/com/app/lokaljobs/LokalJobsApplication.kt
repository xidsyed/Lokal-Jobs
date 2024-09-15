package com.app.lokaljobs

import android.app.Application
import android.content.Context

class LokalJobsApplication : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: LokalJobsApplication? = null

        fun context(): Context {
            return instance!!.applicationContext
        }
    }
}