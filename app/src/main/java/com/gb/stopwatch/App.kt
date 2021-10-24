package com.gb.stopwatch

import android.app.Application
import com.gb.stopwatch.di.aboutActivityModel
import com.gb.stopwatch.di.applicationModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(applicationModule, aboutActivityModel))
        }
    }
}