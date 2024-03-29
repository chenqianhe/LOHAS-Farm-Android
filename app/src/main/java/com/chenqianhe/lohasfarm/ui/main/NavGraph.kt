package com.chenqianhe.lohasfarm.ui.main

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.*
import com.chenqianhe.lohasfarm.logic.utils.LfState
import com.chenqianhe.lohasfarm.ui.main.nav.Actions
import com.chenqianhe.lohasfarm.ui.main.nav.Destinations
import com.chenqianhe.lohasfarm.ui.page.message.DetailMessagePage
import com.chenqianhe.lohasfarm.ui.page.LoginPage
import com.chenqianhe.lohasfarm.ui.page.WebPage
import com.chenqianhe.lohasfarm.ui.page.farm.*
import com.chenqianhe.lohasfarm.ui.page.mine.PersonalInfoPage
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    startDestination: String = Destinations.MAIN_ROUTE
) {
    val navController = rememberAnimatedNavController()
    val actions = remember(navController) { Actions(navController) }
    val finalStartDestination = if (LfState.isLogin) {
        startDestination
    } else {
        Destinations.LOGIN_ROUTE
    }
    AnimatedNavHost(
        navController = navController,
        startDestination = finalStartDestination
    ) {
        composableHorizontal(Destinations.MAIN_ROUTE) {
            MainPage(actions = actions)
        }

        composableHorizontal(Destinations.LOGIN_ROUTE) {
            LoginPage(actions = actions)
        }

        composableHorizontal(Destinations.TUTOR_ROUTE) {
            PlantingTutorialPage(actions = actions)
        }

        composableHorizontal(Destinations.CROP_MANGE_ROUTE) {
            CropManagementPage(actions = actions)
        }

        composableHorizontal("${Destinations.DETAIL_MESSAGE_ROUTE}/{id}/{title}",
            arguments = listOf(navArgument("id") {type = NavType.StringType})
            ) {
                val argument = requireNotNull(it.arguments)
                val id = argument.getString("id")
                val title = argument.getString("title")
                DetailMessagePage(actions = actions, sequenceUid = id!!, title = title!!)
        }

        composableHorizontal("${Destinations.WEB_PAGE_ROUTE}/{url}/{title}",
            arguments = listOf(
                navArgument("url") {type = NavType.StringType},
                navArgument("title") {type = NavType.StringType}
            )) {
                val argument = requireNotNull(it.arguments)
                val url = argument.getString("url")
                val title = argument.getString("title")
                WebPage(actions = actions, url = url!!, title = title!!)
        }

        composableHorizontal("${Destinations.PERSONAL_INFO_ROUTE}/{uid}/{ugid}/{name}/{profile_photo_url}",
            arguments = listOf(
                navArgument("uid") {type = NavType.StringType},
                navArgument("ugid") {type = NavType.StringType},
                navArgument("name") {type = NavType.StringType},
                navArgument("profile_photo_url") {type = NavType.StringType},
            )) {
            val argument = requireNotNull(it.arguments)
            val uid = argument.getString("uid")
            val ugid = argument.getString("ugid")
            val name = argument.getString("name")
            val profilePhotoUrl = argument.getString("profile_photo_url")
            PersonalInfoPage(actions = actions, uid = uid!!, ugid = ugid!!, name = name!!, profile_photo_url = profilePhotoUrl!!)
        }

        composableHorizontal("${Destinations.OTHERS_LAND_ROUTE}/{uid}/{name}/{landPlantedArea}/{landTotalArea}/{profile_photo_url}",
            arguments = listOf(
                navArgument("uid") {type = NavType.StringType},
                navArgument("name") {type = NavType.StringType},
                navArgument("landPlantedArea") {type = NavType.IntType},
                navArgument("landTotalArea") {type = NavType.IntType},
                navArgument("profile_photo_url") {type = NavType.StringType}
            )) {
            val argument = requireNotNull(it.arguments)
            val uid = argument.getString("uid")
            val name = argument.getString("name")
            val landPlantedArea = argument.getInt("landPlantedArea")
            val landTotalArea = argument.getInt("landTotalArea")
            val profilePhotoUrl = argument.getString("profile_photo_url")
            OthersLandDetail(actions, uid!!, name!!, landPlantedArea, landTotalArea, profilePhotoUrl!!)
        }

        composableHorizontal("${Destinations.MINE_LAND_ROUTE}/{uid}/{name}/{landPlantedArea}/{landTotalArea}/{profile_photo_url}/{landLeaseTerm}",
            arguments = listOf(
                navArgument("uid") {type = NavType.StringType},
                navArgument("name") {type = NavType.StringType},
                navArgument("landPlantedArea") {type = NavType.IntType},
                navArgument("landTotalArea") {type = NavType.IntType},
                navArgument("profile_photo_url") {type = NavType.StringType},
                navArgument("landLeaseTerm") {type = NavType.StringType}
            )) {
            val argument = requireNotNull(it.arguments)
            val uid = argument.getString("uid")
            val name = argument.getString("name")
            val landPlantedArea = argument.getInt("landPlantedArea")
            val landTotalArea = argument.getInt("landTotalArea")
            val profilePhotoUrl = argument.getString("profile_photo_url")
            val landLeaseTerm = argument.getString("landLeaseTerm")
            MineLandPage(actions, uid!!, name!!, landPlantedArea, landTotalArea, profilePhotoUrl!!, landLeaseTerm!!)
        }

        composableHorizontal("${Destinations.MINE_LAND_INFO_ROUTE}/{name}/{landLeaseTerm}/{area}",
            arguments = listOf(
                navArgument("name") {type = NavType.StringType},
                navArgument("landLeaseTerm") {type = NavType.StringType},
                navArgument("area") {type = NavType.StringType}
            )) {
            val argument = requireNotNull(it.arguments)
            val name = argument.getString("name")
            val landLeaseTerm = argument.getString("landLeaseTerm")
            val area = argument.getString("area")
            LandInfoPage(actions, name!!, landLeaseTerm!!, area!!)
        }
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.composableHorizontal(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    this@composableHorizontal.composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        content = content,
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
    )
}

@ExperimentalAnimationApi
fun NavGraphBuilder.composableVertical(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    this@composableVertical.composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Up,
                animationSpec = tween(300)
            )
        },
        content = content,
    )
}