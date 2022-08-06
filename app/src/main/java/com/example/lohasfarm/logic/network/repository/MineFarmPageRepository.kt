package com.example.lohasfarm.logic.network.repository

import com.example.lohasfarm.logic.network.LfNetwork

class MineFarmPageRepository {
    suspend fun getPlantAddableData(userUuid: String) = LfNetwork.getPlantAddable(userUuid)

    suspend fun uploadPlantChangeData(userUuid: String, changeInfo: String) =
        LfNetwork.uploadPlantChange(userUuid, changeInfo)
}