package com.jacks_lan.learningappjetpackcompose.account_access.navigation

sealed class AccountAccessScreen(val route: String) {
    object LoginScreen: AccountAccessScreen("login")
    object HomeScreen: AccountAccessScreen("home")
}
