package com.example.lohasfarm.logic.network.repository

import com.example.lohasfarm.logic.network.LfNetwork

class MessagePageRepository {
    suspend fun getSequenceData(userUuid: String) = LfNetwork.getSequenceInfo(userUuid)

    suspend fun getDetailMessageData(userUuid: String, sequenceUid: String) =
        LfNetwork.getDetailMessageInfo(userUuid, sequenceUid)
}