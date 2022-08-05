package com.example.lohasfarm.logic.network.service

import com.example.lohasfarm.logic.network.model.ActivityModel
import com.example.lohasfarm.logic.network.model.BaseModel
import retrofit2.http.GET
import retrofit2.http.Header

interface ActivityPageService {
    @GET("activity/food")
    suspend fun getFoodActivityInfo(
        @Header("X-WX-OPENID") uuid: String,
    ): BaseModel<List<ActivityModel>>

    @GET("activity/farm")
    suspend fun getFarmActivityInfo(
        @Header("X-WX-OPENID") uuid: String,
    ): BaseModel<List<ActivityModel>>

}
