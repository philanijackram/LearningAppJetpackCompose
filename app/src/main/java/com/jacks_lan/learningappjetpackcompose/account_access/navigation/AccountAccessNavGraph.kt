package com.jacks_lan.learningappjetpackcompose.account_access.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jacks_lan.learningappjetpackcompose.account_access.presentation.feature.home.HomeScreen
import com.jacks_lan.learningappjetpackcompose.account_access.presentation.feature.login.ui.LoginScreen

@Composable
fun AccountAccessNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = AccountAccessScreen.LoginScreen.route,
    ) {
        composable(AccountAccessScreen.LoginScreen.route) {
            LoginScreen(){
                navController.navigate(AccountAccessScreen.HomeScreen.route)
            }
        }
        composable(AccountAccessScreen.HomeScreen.route) {
            HomeScreen()
        }

    }

}