package com.jacks_lan.learningappjetpackcompose.account_access.domain.repository

import com.jacks_lan.learningappjetpackcompose.account_access.domain.model.User

interface AccountAccessRepository {

    suspend fun login(email: String, password: String): User?
}
