package com.example.lohasfarm

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.lohasfarm.logic.utils.DataStoreUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
    }

    private var dataStore = DataStoreUtils

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        DataStoreUtils.init(applicationContext)


    }

}