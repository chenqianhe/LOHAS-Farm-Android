package com.chenqianhe.lohasfarm.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController


private val LfDarkColorPalette = LfColors(
    navBar = navBar,
    tabSelected = tabSelected,
    tabDefault = tabDefault,
    headline = headline,
    emphasize = emphasize,
    body1 = body1,
    body2 = body2,
    footnote = footnote,
    background = background,
    white = white,
    black = black,
    default = default,
    color = color,
    color1 = color1,
    color2 = color2,
    color3 = color3,
    color4 = color4,
    color5 = color5,
    color6 = color6,
    color7 = color7,
    isLight = false
)

private val LfLightColorPalette = LfColors(
    navBar = navBar,
    tabSelected = tabSelected,
    tabDefault = tabDefault,
    headline = headline,
    emphasize = emphasize,
    body1 = body1,
    body2 = body2,
    footnote = footnote,
    background = background,
    white = white,
    black = black,
    default = default,
    color = color,
    color1 = color1,
    color2 = color2,
    color3 = color3,
    color4 = color4,
    color5 = color5,
    color6 = color6,
    color7 = color7,
    isLight = true
)


@Composable
fun LOHASFarmTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        LfDarkColorPalette
    } else {
        LfLightColorPalette
    }
    val typography = if (darkTheme) {
        LfDarkFontPalette
    } else {
        LfLightFontPalette
    }

    val sysUiController = rememberSystemUiController()
    SideEffect {
        sysUiController.setSystemBarsColor(
            color = colors.background.copy(),
            darkIcons = true
        )
    }

    ProvideLfColors(colors) {
        MaterialTheme(
            typography = typography,
            shapes = Shapes,
            content = content
        )
    }
}


object LOHASFarmTheme {
    val colors: LfColors
        @Composable
        get() = LocalLfColors.current
}


@Composable
fun ProvideLfColors(colors: LfColors, content: @Composable () -> Unit) {
    val colorPalette = remember {
        colors.copy()
    }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalLfColors provides colorPalette, content = content)
}


private val LocalLfColors = staticCompositionLocalOf {
    LfLightColorPalette
}
