package com.example.plantyreminder.data.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.plantyreminder.MainActivity
import com.example.plantyreminder.R

class AppNotification : BroadcastReceiver() {
    private val id: Int = (1..Int.MAX_VALUE).random()
    private var notificationManager: NotificationManager? = null
    private fun post(notification: android.app.Notification) {
        notificationManager?.notify(
            id, notification
        )
    }

    private fun createNotificationChannel(context: Context) {
        val channel = NotificationChannel(
            context.getString(R.string.notification_reminder_channel),
            context.getString(R.string.notification_reminder),
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = context.getString(R.string.notification_reminder_desc)
        }

        // Register the channel with the system
        notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager?
        notificationManager?.createNotificationChannel(channel)
    }

    private fun createNotification(content: String, context: Context): android.app.Notification {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(
            context,
            context.getString(R.string.notification_reminder_channel)
        )
            .setSmallIcon(R.drawable.plant_image)
            .setContentTitle("Time to water your plants")
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            // Set the intent that will fire when the user taps the notification
            .setAutoCancel(true)
            .build()

    }

    override fun onReceive(context: Context?, intent: Intent?) {
        assert(context != null && intent != null)

        createNotificationChannel(context!!)
        this.post(
            createNotification(intent!!.getStringExtra("content") ?: "", context)
        )
    }

}