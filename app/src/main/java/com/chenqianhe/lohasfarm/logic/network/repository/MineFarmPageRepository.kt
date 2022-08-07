package com.chenqianhe.lohasfarm.logic.network.repository

import com.chenqianhe.lohasfarm.logic.network.LfNetwork

class MineFarmPageRepository {
    suspend fun getPlantAddableData(userUuid: String) = LfNetwork.getPlantAddable(userUuid)

    suspend fun getPlantIntroData(userUuid: String) = LfNetwork.getPlantIntroInfo(userUuid)

    suspend fun uploadPlantChangeData(userUuid: String, changeInfo: String) =
        LfNetwork.uploadPlantChange(userUuid, changeInfo)
}