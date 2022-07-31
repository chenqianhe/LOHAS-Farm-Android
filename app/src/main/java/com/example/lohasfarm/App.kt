package com.example.lohasfarm

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.lohasfarm.logic.database.FarmInfoDao
import com.example.lohasfarm.logic.database.FarmInfoDatabase
import com.example.lohasfarm.logic.utils.DataStoreUtils
import com.example.lohasfarm.logic.utils.LfState
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
    }

    private var dataStore = DataStoreUtils
    private lateinit var farmInfoDao: FarmInfoDao

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        farmInfoDao = Room.databaseBuilder(applicationContext,
            FarmInfoDatabase::class.java, FARM_INFO_DATABASE_NAME).allowMainThreadQueries()
            .build().farmInfoDao()

        DataStoreUtils.init(applicationContext)
        LfState.initialize(dataStore, farmInfoDao)
    }

}