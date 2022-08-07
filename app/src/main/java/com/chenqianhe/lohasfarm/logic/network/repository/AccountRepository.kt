package com.chenqianhe.lohasfarm.logic.network.repository

import com.chenqianhe.lohasfarm.logic.network.LfNetwork

class AccountRepository {
    suspend fun login(account: String, password: String) =
        LfNetwork.login(account, password)
}