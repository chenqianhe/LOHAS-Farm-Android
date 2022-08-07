package com.chenqianhe.lohasfarm.logic.network.repository

import com.chenqianhe.lohasfarm.logic.network.LfNetwork

class MessagePageRepository {
    suspend fun getSequenceData(userUuid: String) = LfNetwork.getSequenceInfo(userUuid)

    suspend fun getDetailMessageData(userUuid: String, sequenceUid: String) =
        LfNetwork.getDetailMessageInfo(userUuid, sequenceUid)
}