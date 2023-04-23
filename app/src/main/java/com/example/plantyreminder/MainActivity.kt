package com.example.plantyreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.plantyreminder.di.appModule
import com.example.plantyreminder.ui.PlantyReminderApp
import com.example.plantyreminder.ui.theme.PlantyReminderTheme
import com.example.plantyreminder.ui.home.Home
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
