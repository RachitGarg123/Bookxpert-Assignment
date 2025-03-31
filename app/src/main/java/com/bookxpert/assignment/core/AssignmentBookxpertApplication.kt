package com.bookxpert.assignment.core

import android.app.Application
import android.content.Context
import com.bookxpert.assignment.notification.domain.createNotificationChannel
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AssignmentBookxpertApplication: Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        createNotificationChannel(appContext)
    }
}