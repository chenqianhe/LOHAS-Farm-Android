package com.example.lohasfarm.ui.main.nav

import androidx.navigation.NavHostController

class Actions(navController: NavHostController) {

    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}