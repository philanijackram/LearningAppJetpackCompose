package com.jacks_lan.learningappjetpackcompose.account_access.data.repository

import com.jacks_lan.learningappjetpackcompose.account_access.data.remote.api.AccountAccessApi
import com.jacks_lan.learningappjetpackcompose.account_access.data.remote.dto.LoginRequestDto
import com.jacks_lan.learningappjetpackcompose.account_access.domain.model.User
import com.jacks_lan.learningappjetpackcompose.account_access.domain.repository.AccountAccessRepository
import javax.inject.Inject

class AccountAccessRepositoryImpl @Inject constructor(
    private val api: AccountAccessApi
) : AccountAccessRepository {

    override suspend fun login(
        email: String,
        password: String
    ): User? {
        val userResponse = api.login(loginRequest = LoginRequestDto(email, password))
        return if (userResponse.isSuccessful) {
            User(
                id = userResponse.body()?.id ?: "",
                name = userResponse.body()?.name ?: "",
                email = userResponse.body()?.email ?: ""
            )
        } else {
            null

        }

    }

}