package com.example.lohasfarm.logic.network.repository

import com.example.lohasfarm.logic.network.LfNetwork

class PlantingTutorialRepository {
    suspend fun getTutorData(userUuid: String) = LfNetwork.getTutorInfo(userUuid)
}