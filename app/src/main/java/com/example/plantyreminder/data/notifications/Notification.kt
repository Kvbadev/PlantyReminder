package com.example.plantyreminder.data.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.plantyreminder.R
import kotlin.random.Random

class Notification(
    private val notificationType: NotificationType,
    context: Context,
    private val importanceLevel: Int = NotificationManager.IMPORTANCE_DEFAULT,
    private val channelId: String = "my_channel_01",
) {
    private var notificationManager: NotificationManager? = null
    private lateinit var notificationBuilder: NotificationCompat.Builder

    init {
        createNotificationChannel(context)
        createNotification(context)
    }
    fun show() {
        notificationManager?.notify(
            123,
            notificationBuilder.build()
        )
    }
    private fun createNotificationChannel(context: Context) {
        val name = context.getString(notificationType.nameId)
        val descriptionText = context.getString(notificationType.descriptionId)
        val importance = importanceLevel
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }

        // Register the channel with the system
        notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager?
        notificationManager?.createNotificationChannel(channel)
    }
    private fun createNotification(context: Context) {
        // Create an explicit intent for an Activity in your app
        val intent = Intent(context, Notification::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.plant_image)
            .setContentTitle("My notification")
            .setContentText("Hello World!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

    }
}