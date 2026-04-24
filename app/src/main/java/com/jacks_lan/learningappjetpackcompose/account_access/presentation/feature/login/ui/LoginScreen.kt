package com.jacks_lan.learningappjetpackcompose.account_access.presentation.feature.login.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jacks_lan.learningappjetpackcompose.account_access.presentation.feature.login.state.LoginEffect
import com.jacks_lan.learningappjetpackcompose.account_access.presentation.feature.login.state.LoginEvent

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit = {}
) {

    val state by viewModel.state.collectAsState()

    val context = LocalContext.current

    // EFFECT HANDLER
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                LoginEffect.ShowSuccess -> {
                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                }

                is LoginEffect.ShowError -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }

                LoginEffect.NavigateToHome -> {
                    onNavigateToHome()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text("Login", fontSize = 28.sp)

        Spacer(Modifier.height(16.dp))

        TextField(
            value = state.email,
            onValueChange = {
                viewModel.onEvent(LoginEvent.EmailChanged(it))
            },
            label = { Text("Email") }
        )

        Spacer(Modifier.height(12.dp))

        TextField(
            value = state.password,
            onValueChange = {
                viewModel.onEvent(LoginEvent.PasswordChanged(it))
            },
            label = { Text("Password") }
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.onEvent(LoginEvent.LoginClicked)
            }
        ) {
            Text("Login")
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}