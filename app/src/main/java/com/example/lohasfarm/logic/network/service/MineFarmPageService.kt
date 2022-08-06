package com.example.lohasfarm.logic.network.service

import com.example.lohasfarm.logic.network.model.BaseModel
import com.example.lohasfarm.logic.network.model.FullPlantInfoModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface MineFarmPageService {
    @GET("plant/addable")
    suspend fun getPlantAddableInfo(
        @Header("X-WX-OPENID") uuid: String,
    ): BaseModel<List<FullPlantInfoModel>>

    @POST("/plant/change")
    suspend fun uploadPlantChange(
        @Header("X-WX-OPENID") uuid: String,
        @Query("change_info") changeInfo: String
    ): BaseModel<List<FullPlantInfoModel>>

}