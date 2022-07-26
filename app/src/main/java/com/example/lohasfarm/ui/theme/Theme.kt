package com.example.lohasfarm.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController


enum class StylePallet {
    DARK,
    LIGHT
}


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
    isLight = true
)


@Composable
fun LOHASFarmTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        LfDarkColorPalette
    } else {
        LfLightColorPalette
    }
    val sysUiController = rememberSystemUiController()
    SideEffect {
        sysUiController.setSystemBarsColor(
            color = colors.background.copy()
        )
    }

    ProvideLfColors(colors) {
        MaterialTheme(
            typography = Typography,
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
