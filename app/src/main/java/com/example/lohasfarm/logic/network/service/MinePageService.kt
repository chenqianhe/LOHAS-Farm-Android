package com.example.lohasfarm.logic.network.service

import com.example.lohasfarm.logic.network.model.BaseModel
import com.example.lohasfarm.logic.network.model.UserInfoModel
import retrofit2.http.GET
import retrofit2.http.Header

interface MinePageService {

    @GET("user")
    suspend fun getUserInfo(
        @Header("X-WX-OPENID") uuid: String
    ): BaseModel<UserInfoModel>

}
