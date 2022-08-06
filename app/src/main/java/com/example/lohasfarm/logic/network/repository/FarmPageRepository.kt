package com.example.lohasfarm.logic.network.repository

import com.example.lohasfarm.logic.network.LfNetwork

class FarmPageRepository {
    suspend fun getLandData(userUuid: String) = LfNetwork.getLandInfo(userUuid)

    suspend fun getPlantIntroData(userUuid: String) = LfNetwork.getPlantIntroInfo(userUuid)

    suspend fun getOthersPlantData(userUuid: String, landUid: String) =
        LfNetwork.getOthersPlantIntroInfo(userUuid, landUid)

}