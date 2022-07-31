package com.example.lohasfarm.logic.utils

import com.example.lohasfarm.logic.network.model.BaseModel
import com.example.lohasfarm.logic.network.model.LoginModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * 全局数据接口
 */
object LfState {
    private const val USER_UUID = "uuid"
    private const val USER_UGID = "ugid"
    private const val USER_UID = "uid"
    private const val USER_ADMIN = "admin"
    private const val IS_LOGIN = "isLogin"
    private lateinit var dataStore: DataStoreUtils

    /**
     * 初始化
     */
    fun initialize(dataStoreUtils: DataStoreUtils) {
        dataStore = dataStoreUtils
    }

    /**
     * 清除所有信息
     */
    @OptIn(DelicateCoroutinesApi::class)
    fun clearAll()= GlobalScope.launch{
        dataStore.clear()
    }

    /**
     * 登陆状态
     */
    var isLogin: Boolean
        get() = dataStore.getDataSync(IS_LOGIN, false)
        set(value) {
            dataStore.putDataSync(IS_LOGIN, value)
        }

    /**
     * UUID
     */
    var uuid: String
        get() = dataStore.getDataSync(USER_UUID, "0")
        set(value) {
            dataStore.putDataSync(USER_UUID, value)
        }

    /**
     * UID
     */
    var uid: String
        get() = dataStore.getDataSync(USER_UID, "0")
        set(value) {
            dataStore.putDataSync(USER_UID, value)
        }

    /**
     * UGID
     */
    var ugid: String
        get() = dataStore.getDataSync(USER_UGID, "0")
        set(value) {
            dataStore.putDataSync(USER_UGID, value)
        }

    /**
     * UUID
     */
    var admin: Int
        get() = dataStore.getDataSync(USER_ADMIN, 0)
        set(value) {
            dataStore.putDataSync(USER_ADMIN, value)
        }

    /**
     * 存储用户基本信息
     */
    fun saveLoginState(loginModel: BaseModel<LoginModel>) {
        if (loginModel.code == StateCode.LoginSuccess.code) {
            val loginContent = loginModel.content

            this.uuid = loginContent.uuid
            this.uid = loginContent.uid
            this.ugid = loginContent.ugid
            this.admin = loginContent.admin
        }
    }

}