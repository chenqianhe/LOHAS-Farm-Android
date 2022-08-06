package com.example.lohasfarm.ui.main.nav

import androidx.annotation.StringRes
import androidx.navigation.NavHostController

class Actions(navController: NavHostController) {

    val toMainPage: () -> Unit = {
        navController.navigate(Destinations.MAIN_ROUTE)
    }

    val toLoginPage: () -> Unit = {
        navController.navigate(Destinations.LOGIN_ROUTE)
    }

    val toWebPage: (url: String, title: String) -> Unit = { url: String, title: String ->
        navController.navigate("${Destinations.WEB_PAGE_ROUTE}/${url}/${title}")
    }

    val upPress: () -> Unit = {
        navController.navigateUp()
    }

    val toDetailMessagePage: (id: String, title: String) -> Unit = { id: String, title: String ->
        navController.navigate("${Destinations.DETAIL_MESSAGE_ROUTE}/${id}/${title}")
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