package com.chenqianhe.lohasfarm.logic.network.service

import com.chenqianhe.lohasfarm.logic.network.model.BaseModel
import com.chenqianhe.lohasfarm.logic.network.model.TutorModel
import retrofit2.http.GET
import retrofit2.http.Header

interface PlantingTutorialService {

    @GET("tutor")
    suspend fun getTutorInfo(
        @Header("X-WX-OPENID") uuid: String
    ): BaseModel<List<TutorModel>>

}