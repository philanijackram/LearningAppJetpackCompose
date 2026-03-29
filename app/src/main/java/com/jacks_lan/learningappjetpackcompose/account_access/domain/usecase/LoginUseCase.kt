package com.jacks_lan.learningappjetpackcompose.account_access.domain.usecase

import com.jacks_lan.learningappjetpackcompose.account_access.domain.model.User
import com.jacks_lan.learningappjetpackcompose.account_access.domain.repository.AccountAccessRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AccountAccessRepository
) {

    suspend operator fun invoke(email: String, password: String): User? {
        return repository.login(email, password)
    }

}