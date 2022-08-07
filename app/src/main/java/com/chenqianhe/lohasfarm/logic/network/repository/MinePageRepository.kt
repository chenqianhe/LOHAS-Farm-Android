package com.chenqianhe.lohasfarm.logic.network.repository

import com.chenqianhe.lohasfarm.logic.network.LfNetwork

class MinePageRepository {
    suspend fun getUserData(userUuid: String) = LfNetwork.getUserInfo(userUuid)
}