package com.jacks_lan.learningappjetpackcompose.account_access.presentation.feature.login.state

import com.jacks_lan.learningappjetpackcompose.account_access.domain.model.User

data class LoginUiState (
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val user: User? = null,)


sealed class LoginEffect {
    data class ShowError(val message: String) : LoginEffect()
    object ShowSuccess : LoginEffect()
    object NavigateToHome : LoginEffect()
}

sealed class LoginEvent {
    object LoginClicked : LoginEvent()
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data object OnAuthenticateClicked : LoginEvent()
    data object OnAuthSuccess : LoginEvent()
    data class OnAuthError(val message: String) : LoginEvent()
}
