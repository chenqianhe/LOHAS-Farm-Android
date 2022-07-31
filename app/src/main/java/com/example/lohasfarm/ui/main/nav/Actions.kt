package com.example.lohasfarm.ui.main.nav

import androidx.navigation.NavHostController

class Actions(navController: NavHostController) {

    val toMainPage: () -> Unit = {
        navController.navigate(Destinations.MAIN_ROUTE)
    }

    val toLoginPage: () -> Unit = {
        navController.navigate(Destinations.LOGIN_ROUTE)
    }

    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}