package com.example.lohasfarm.ui.main

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import com.example.lohasfarm.ui.main.nav.Actions
import com.example.lohasfarm.ui.main.nav.Destinations
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    startDestination: String = Destinations.FARM_ROUTE
) {
    val navController = rememberAnimatedNavController()
    val actions = remember(navController) { Actions(navController) }
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composableHorizontal(Destinations.FARM_ROUTE) {
            MainPage(actions = actions)
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