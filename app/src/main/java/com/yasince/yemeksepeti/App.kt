package com.yasince.yemeksepeti

import android.app.Application
import com.facebook.stetho.Stetho
import com.yasince.yemeksepeti.data.AppDataManager
import com.yasince.yemeksepeti.data.DataManager
import com.yasince.yemeksepeti.data.db.AppDbHelper
import com.yasince.yemeksepeti.data.network.AppApiHelper
import com.yasince.yemeksepeti.data.prefs.AppPreferencesHelper

class App : Application() {

    lateinit var dataManager: DataManager
        internal set

    override fun onCreate() {
        super.onCreate()

        val spHelper = AppPreferencesHelper(applicationContext)
        val apiHelper = AppApiHelper()
        val dbHelper = AppDbHelper(applicationContext)
        dataManager = AppDataManager(applicationContext, spHelper, apiHelper, dbHelper)
        Stetho.initializeWithDefaults(this)
    }
}
