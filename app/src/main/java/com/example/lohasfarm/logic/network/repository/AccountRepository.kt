package com.example.lohasfarm.logic.network.repository

import com.example.lohasfarm.logic.network.LfNetwork

class AccountRepository {
    suspend fun login(account: String, password: String) =
        LfNetwork.login(account, password)
}