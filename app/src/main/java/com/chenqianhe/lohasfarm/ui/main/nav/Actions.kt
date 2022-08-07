package com.chenqianhe.lohasfarm.ui.main.nav

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

    val toTutorPage: () -> Unit = {
        navController.navigate(Destinations.TUTOR_ROUTE)
    }

    val toCropManagementPage: () -> Unit = {
        navController.navigate(Destinations.CROP_MANGE_ROUTE)
    }

    val upPress: () -> Unit = {
        navController.navigateUp()
    }

    val toDetailMessagePage: (id: String, title: String) -> Unit = { id: String, title: String ->
        navController.navigate("${Destinations.DETAIL_MESSAGE_ROUTE}/${id}/${title}")
    }

    val toPersonalInfo: (uid: String, ugid: String, name: String, profile_photo_url: String) -> Unit
            = { uid: String, ugid: String, name: String, profile_photo_url: String ->
        navController
            .navigate("${Destinations.PERSONAL_INFO_ROUTE}/${uid}/${ugid}/${name}/${profile_photo_url}")
    }

    val toOthersLandDetail: (uid: String, name: String, landPlantedArea: Int, landTotalArea: Int, profile_photo_url: String) -> Unit
            = { uid: String, name: String, landPlantedArea: Int, landTotalArea: Int, profile_photo_url: String ->
        navController
            .navigate("${Destinations.OTHERS_LAND_ROUTE}/${uid}/${name}/${landPlantedArea}/${landTotalArea}/${profile_photo_url}")
    }

    val toMineLandDetail: (uid: String, name: String, landPlantedArea: Int, landTotalArea: Int, profile_photo_url: String, landLeaseTerm: String) -> Unit
            = { uid: String, name: String, landPlantedArea: Int, landTotalArea: Int, profile_photo_url: String, landLeaseTerm: String ->
        navController
            .navigate("${Destinations.MINE_LAND_ROUTE}/${uid}/${name}/${landPlantedArea}/${landTotalArea}/${profile_photo_url}/${landLeaseTerm}")
    }

    val toMineLandInfo: ( name: String, landLeaseTerm: String, area: String) -> Unit
            = {  name: String, landLeaseTerm: String, area: String ->
        navController
            .navigate("${Destinations.MINE_LAND_INFO_ROUTE}/${name}/${landLeaseTerm}/${area}")
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