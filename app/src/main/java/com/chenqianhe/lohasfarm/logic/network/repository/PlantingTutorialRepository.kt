package com.chenqianhe.lohasfarm.logic.network.repository

import com.chenqianhe.lohasfarm.logic.network.LfNetwork

class PlantingTutorialRepository {
    suspend fun getTutorData(userUuid: String) = LfNetwork.getTutorInfo(userUuid)
}