package com.example.lohasfarm.logic.network

import com.example.lohasfarm.IS_TEST_ENV
import com.example.lohasfarm.logic.network.service.*

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
     * 活动页相关服务
     */
    private val activityPageService = ServiceCreator.create(ActivityPageService::class.java, IS_TEST_ENV)

    /**
     * 消息页相关服务
     */
    private val messagePageService = ServiceCreator.create(MessagePageService::class.java, IS_TEST_ENV)

    /**
     * 个人页相关服务
     */
    private val minePageService = ServiceCreator.create(MinePageService::class.java, IS_TEST_ENV)

    /**
     * 登陆
     */
    suspend fun login(account: String, password: String) = loginService.login(account, password)

    /**
     * 获取土地信息
     */
    suspend fun getLandInfo(userUuid: String) = farmPageService.getLandInfo(userUuid)

    /**
     * 获取作物信息
     */
    suspend fun getPlantIntroInfo(userUuid: String) = farmPageService.getPlantIntroInfo(userUuid)

    /**
     * 获取农副食品信息
     */
    suspend fun getFoodActivityInfo(userUuid: String) = activityPageService.getFoodActivityInfo(userUuid)

    /**
     * 获取农场活动信息
     */
    suspend fun getFarmActivityInfo(userUuid: String) = activityPageService.getFarmActivityInfo(userUuid)

    /**
     * 获取农场通知概览
     */
    suspend fun getSequenceInfo(userUuid: String) = messagePageService.getSequenceInfo(userUuid)

    /**
     * 获取农场通知详情
     */
    suspend fun getDetailMessageInfo(userUuid: String, sequenceUid: String) =
        messagePageService.getDetailMessageInfo(userUuid, sequenceUid)

    /**
     * 获取个人信息
     */
    suspend fun getUserInfo(userUuid: String) = minePageService.getUserInfo(userUuid)

}
