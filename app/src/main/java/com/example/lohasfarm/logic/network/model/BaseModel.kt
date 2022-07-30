package com.example.lohasfarm.logic.network.model

/**
 * 数据基础类，所有收到的数据都会包括状态码，状态信息和数据
 */
data class BaseModel<T>(
    val content: T,
    val code: Int,
    val msg: String
)
