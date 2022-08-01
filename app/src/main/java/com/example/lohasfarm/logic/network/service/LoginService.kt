package com.example.lohasfarm.logic.network.service

import com.example.lohasfarm.logic.network.model.BaseModel
import com.example.lohasfarm.logic.network.model.LoginModel
import retrofit2.http.GET
import retrofit2.http.Header


interface LoginService {

    @GET("login/app")
    suspend fun login(
        @Header("X-WX-ID") account: String,
        @Header("X-WX-PASSWORD") password: String
    ): BaseModel<LoginModel>


}