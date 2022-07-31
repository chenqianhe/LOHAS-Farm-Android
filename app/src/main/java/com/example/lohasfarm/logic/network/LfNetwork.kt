package com.example.lohasfarm.logic.network

import com.example.lohasfarm.IS_TEST_ENV
import com.example.lohasfarm.logic.network.service.FarmPageService
import com.example.lohasfarm.logic.network.service.LoginService

object LfNetwork {

    /**
     * 登陆注册等相关服务
     */
    private val loginService = ServiceCreator.create(LoginService::class.java, IS_TEST_ENV)

    /**
     * 首页相关服务
     */
    private val farmPageService = ServiceCreator.create(FarmPageService::class.java, IS_TEST_ENV)

    /**
     * 登陆
     */
    suspend fun login(account: String, password: String) = loginService.login(account, password)

    /**
     * 获取土地信息
     */
    suspend fun getLandInfo(userUuid: String) = farmPageService.getLandInfo(userUuid)

}
