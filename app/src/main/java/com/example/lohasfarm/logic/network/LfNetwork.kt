package com.example.lohasfarm.logic.network

import com.example.lohasfarm.IS_TEST_ENV
import com.example.lohasfarm.logic.network.service.LoginService

object LfNetwork {

    /**
     * 登陆注册等相关服务
     */
    private val loginService = ServiceCreator.create(LoginService::class.java, IS_TEST_ENV)

    /**
     * 登陆
     */
    suspend fun login(account: String, password: String) = loginService.login(account, password)

}