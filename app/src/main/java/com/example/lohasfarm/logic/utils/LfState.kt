package com.example.lohasfarm.logic.utils


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
     * 判断登陆
     */
    var isLogin: Boolean
        get() = dataStore.getDataSync(IS_LOGIN, false)
        set(b) {
            dataStore.putDataSync(IS_LOGIN, b)
        }


}