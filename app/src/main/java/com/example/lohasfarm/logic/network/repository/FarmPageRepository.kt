package com.example.lohasfarm.logic.network.repository

import com.example.lohasfarm.logic.network.LfNetwork

class FarmPageRepository {
    suspend fun getLandData(userUuid: String) = LfNetwork.getLandInfo(userUuid)

}