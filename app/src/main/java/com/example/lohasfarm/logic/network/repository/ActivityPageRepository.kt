package com.example.lohasfarm.logic.network.repository

import com.example.lohasfarm.logic.network.LfNetwork

class ActivityPageRepository {
    suspend fun getFoodActivityData(userUuid: String) = LfNetwork.getFoodActivityInfo(userUuid)

    suspend fun getFarmActivityData(userUuid: String) = LfNetwork.getFarmActivityInfo(userUuid)

}