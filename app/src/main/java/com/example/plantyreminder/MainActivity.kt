package com.example.plantyreminder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.NotificationCompat
import com.example.plantyreminder.di.appModule
import com.example.plantyreminder.ui.PlantyReminderApp
import com.example.plantyreminder.ui.theme.PlantyReminderTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }

        setContent {
            PlantyReminderTheme {
                PlantyReminderApp()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }

    companion object {
        var isActivityVisible: Boolean = false
    }

    override fun onResume() {
        super.onResume()
        isActivityVisible = !isActivityVisible
    }

    override fun onPause() {
        super.onPause()
        isActivityVisible = !isActivityVisible
    }

}
