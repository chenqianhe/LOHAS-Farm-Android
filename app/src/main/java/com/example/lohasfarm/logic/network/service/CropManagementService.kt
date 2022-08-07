package com.example.lohasfarm.logic.network.service

import com.example.lohasfarm.logic.network.model.BaseModel
import com.example.lohasfarm.logic.network.model.OpModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CropManagementService {
    @GET("operation")
    suspend fun getOperationInfo(
        @Header("X-WX-OPENID") uuid: String
    ): BaseModel<List<OpModel>>

}