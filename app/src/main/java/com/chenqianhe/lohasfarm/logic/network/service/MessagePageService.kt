package com.chenqianhe.lohasfarm.logic.network.service

import com.chenqianhe.lohasfarm.logic.network.model.BaseModel
import com.chenqianhe.lohasfarm.logic.network.model.DetailMessageModel
import com.chenqianhe.lohasfarm.logic.network.model.SequenceInfoModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface MessagePageService {
    @GET("notice/farm")
    suspend fun getSequenceInfo(
        @Header("X-WX-OPENID") uuid: String
    ): BaseModel<List<SequenceInfoModel>>

    @GET("notice/farm/detail")
    suspend fun getDetailMessageInfo(
        @Header("X-WX-OPENID") uuid: String,
        @Query("sequence_uid") sequenceUid: String
    ): BaseModel<List<DetailMessageModel>>

}
