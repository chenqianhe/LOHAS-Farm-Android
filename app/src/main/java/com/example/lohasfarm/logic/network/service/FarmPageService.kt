package com.example.lohasfarm.logic.network.service

import com.example.lohasfarm.logic.network.model.BaseModel
import com.example.lohasfarm.logic.network.model.LandInfoModel
import com.example.lohasfarm.logic.network.model.PlantIntroModel
import com.example.lohasfarm.logic.network.model.TutorModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface FarmPageService {
    @GET("land")
    suspend fun getLandInfo(
        @Header("X-WX-OPENID") uuid: String,
    ): BaseModel<List<LandInfoModel>>

    @GET("plant")
    suspend fun getPlantIntroInfo(
        @Header("X-WX-OPENID") uuid: String,
    ): BaseModel<List<PlantIntroModel>>

    @GET("plant/others")
    suspend fun getOthersPlantIntroInfo(
        @Header("X-WX-OPENID") uuid: String,
        @Query("land_uid") landUid: String
    ): BaseModel<List<PlantIntroModel>>
}
