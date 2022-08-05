package com.example.lohasfarm.logic.network.service

import com.example.lohasfarm.logic.network.model.BaseModel
import com.example.lohasfarm.logic.network.model.LandInfoModel
import com.example.lohasfarm.logic.network.model.PlantIntroModel
import retrofit2.http.GET
import retrofit2.http.Header


interface FarmPageService {
    @GET("land")
    suspend fun getLandInfo(
        @Header("X-WX-OPENID") uuid: String,
    ): BaseModel<List<LandInfoModel>>

    @GET("plant")
    suspend fun getPlantIntroInfo(
        @Header("X-WX-OPENID") uuid: String,
    ): BaseModel<List<PlantIntroModel>>

}
