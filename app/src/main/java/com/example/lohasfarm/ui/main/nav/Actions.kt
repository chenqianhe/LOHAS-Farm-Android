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

    /**
     * 清理栈中页面，实现后续页面返回直接到桌面
     */
    val clearBackStack: () -> Unit = {
        while (!navController.backQueue.isEmpty()) {
            navController.popBackStack(navController.currentDestination!!.id,
                inclusive = true,
                saveState = false
            )
        }
    }
}