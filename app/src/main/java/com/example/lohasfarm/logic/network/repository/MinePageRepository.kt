package com.example.lohasfarm.logic.network.repository

import com.example.lohasfarm.logic.network.LfNetwork

class MinePageRepository {
    suspend fun getUserData(userUuid: String) = LfNetwork.getUserInfo(userUuid)
}