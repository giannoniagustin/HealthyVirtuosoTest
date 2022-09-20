package com.example.healthyvirtuosotest.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HealtVirtuosoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}