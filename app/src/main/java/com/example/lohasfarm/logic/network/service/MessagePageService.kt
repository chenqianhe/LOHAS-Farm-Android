package com.example.lohasfarm.logic.network.service

import com.example.lohasfarm.logic.network.model.BaseModel
import com.example.lohasfarm.logic.network.model.SequenceInfoModel
import retrofit2.http.GET
import retrofit2.http.Header


interface MessagePageService {
    @GET("notice/farm")
    suspend fun getSequenceInfo(
        @Header("X-WX-OPENID") uuid: String
    ): BaseModel<List<SequenceInfoModel>>

}