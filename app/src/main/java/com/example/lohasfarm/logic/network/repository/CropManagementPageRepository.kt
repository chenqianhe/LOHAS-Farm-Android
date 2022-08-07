package com.example.lohasfarm.logic.network.repository

import com.example.lohasfarm.logic.network.LfNetwork

class CropManagementPageRepository {

    suspend fun getPlantIntroData(userUuid: String) = LfNetwork.getPlantIntroInfo(userUuid)

    suspend fun getOperationData(userUuid: String) = LfNetwork.getOperationInfo(userUuid)
}