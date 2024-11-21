package com.knomster.ideas.presentation

import android.app.Application
import com.knomster.ideas.data.AppDataBase
import com.knomster.ideas.domain.AppData

class MainApp: Application() {
    override fun onCreate() {
        super.onCreate()
        val appDao = AppDataBase.getDataBase(this).getDao()
        AppData.setAppDao(appDao)
    }
}