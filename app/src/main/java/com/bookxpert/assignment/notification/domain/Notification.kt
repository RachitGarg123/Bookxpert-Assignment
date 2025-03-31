package com.bookxpert.assignment.notification.domain

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.bookxpert.assignment.R

fun createNotificationChannel(context: Context) {
    val channel = NotificationChannel(
        "delete_channel",
        "Deleted Item Notifications",
        NotificationManager.IMPORTANCE_HIGH
    ).apply {
        description = "Shows notifications when an item is deleted"
    }

    val notificationManager = context.getSystemService(NotificationManager::class.java)
    notificationManager.createNotificationChannel(channel)
}


fun showDeleteNotification(context: Context, objectName: String) {
    val notificationManager = ContextCompat.getSystemService(
        context, NotificationManager::class.java
    ) as NotificationManager

    val notification = NotificationCompat.Builder(context, "delete_channel")
        .setSmallIcon(R.drawable.ic_app_logo) // Use your app icon
        .setContentTitle("Item Deleted")
        .setContentText("$objectName was deleted from the list.")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)
        .build()

    notificationManager.notify(1, notification)
}
