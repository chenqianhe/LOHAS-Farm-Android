package com.example.lohasfarm

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.lohasfarm.logic.database.FarmInfoDao
import com.example.lohasfarm.logic.database.FarmInfoDatabase
import com.example.lohasfarm.logic.database.PlantIntroInfoDao
import com.example.lohasfarm.logic.database.PlantIntroInfoDatabase
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
    private lateinit var plantIntroInfoDao: PlantIntroInfoDao

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        farmInfoDao = Room.databaseBuilder(applicationContext,
            FarmInfoDatabase::class.java, FARM_INFO_DATABASE_NAME).allowMainThreadQueries()
            .build().farmInfoDao()
        plantIntroInfoDao = Room.databaseBuilder(applicationContext,
            PlantIntroInfoDatabase::class.java, PLANT_INTRO_INFO_DATABASE_NAME).allowMainThreadQueries()
            .build().PlantIntroInfoDao()

        DataStoreUtils.init(applicationContext)
        LfState.initialize(dataStore, farmInfoDao, plantIntroInfoDao)
    }

}