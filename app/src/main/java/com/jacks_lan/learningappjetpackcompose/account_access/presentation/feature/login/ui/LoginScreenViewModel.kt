package com.jacks_lan.learningappjetpackcompose.account_access.presentation.feature.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jacks_lan.learningappjetpackcompose.account_access.domain.usecase.LoginUseCase
import com.jacks_lan.learningappjetpackcompose.account_access.presentation.feature.login.state.LoginEffect
import com.jacks_lan.learningappjetpackcompose.account_access.presentation.feature.login.state.LoginEvent
import com.jacks_lan.learningappjetpackcompose.account_access.presentation.feature.login.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    // STATE
    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()

    // EFFECT
    private val _effect = Channel<LoginEffect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {

            is LoginEvent.EmailChanged -> {
                _state.update { it.copy(email = event.email) }
            }

            is LoginEvent.PasswordChanged -> {
                _state.update { it.copy(password = event.password) }
            }

            LoginEvent.LoginClicked -> {
                login()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {

            val current = _state.value

            if (current.email.isBlank() || current.password.isBlank()) {
                _effect.send(LoginEffect.ShowError("Fields cannot be empty"))
                return@launch
            }

            _state.update { it.copy(isLoading = true) }

            try {
                val user = loginUseCase(current.email, current.password)
                _state.update { it.copy(isLoading = false) }
                if (user==null){
                    _effect.send(
                        LoginEffect.ShowError(
                            "Invalid credentials"
                        )
                    )
                }else{
                    _effect.send(LoginEffect.ShowSuccess)
                    _effect.send(LoginEffect.NavigateToHome)
                }



            } catch (e: Exception) {

                _state.update { it.copy(isLoading = false) }

                _effect.send(
                    LoginEffect.ShowError(
                        e.message ?: "Login failed"
                    )
                )
            }
        }
    }
}

