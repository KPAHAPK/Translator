package com.example.dictionary

import android.app.Application
import com.example.dictionary.di.koin.application
import com.example.dictionary.di.koin.historyScreen
import com.example.dictionary.di.koin.mainScreen
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { modules(listOf(application, mainScreen, historyScreen)) }
    }
}