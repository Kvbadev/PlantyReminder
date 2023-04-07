package com.example.plantyreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.plantyreminder.di.appModule
import com.example.plantyreminder.ui.theme.PlantyReminderTheme
import com.example.plantyreminder.views.home.Home
import com.example.plantyreminder.views.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin{
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }

        val x: HomeViewModel by viewModel()

        setContent {
            PlantyReminderTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.white))
                ) {
                    Home();
                }
            }
        }
    }
}
