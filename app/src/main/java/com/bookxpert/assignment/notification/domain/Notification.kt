package com.bookxpert.assignment.notification.domain

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.bookxpert.assignment.R
import com.bookxpert.assignment.core.constants.AppConstants

fun createNotificationChannel(context: Context) {
    val channel = NotificationChannel(
        AppConstants.CHANNEL_ID,
        AppConstants.CHANNEL_NAME,
        NotificationManager.IMPORTANCE_HIGH
    ).apply {
        description = AppConstants.CHANNEL_DESCRIPTION
    }

    val notificationManager = context.getSystemService(NotificationManager::class.java)
    notificationManager.createNotificationChannel(channel)
}


fun showDeleteNotification(context: Context, objectName: String) {
    val notificationManager = ContextCompat.getSystemService(
        context, NotificationManager::class.java
    ) as NotificationManager

    val notification = NotificationCompat.Builder(context, AppConstants.CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_app_logo) // Use your app icon
        .setContentTitle(AppConstants.NOTIFICATION_DELETED)
        .setContentText("$objectName was deleted from the list.")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)
        .build()

    notificationManager.notify(1, notification)
}
