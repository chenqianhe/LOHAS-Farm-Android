package com.chenqianhe.lohasfarm.logic.network.service

import com.chenqianhe.lohasfarm.logic.network.model.BaseModel
import com.chenqianhe.lohasfarm.logic.network.model.OpModel
import retrofit2.http.GET
import retrofit2.http.Header

interface CropManagementService {
    @GET("operation")
    suspend fun getOperationInfo(
        @Header("X-WX-OPENID") uuid: String
    ): BaseModel<List<OpModel>>

}