package com.example.lohasfarm.ui.theme

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color


@Stable
class LfColors(
    navBar: Color,
    tabSelected: Color,
    tabDefault: Color,
    headline: Color,
    emphasize: Color,
    body1: Color,
    body2: Color,
    footnote: Color,
    background: Color,
    white: Color,
    black: Color,
    default: Color,
    color: Color,
    color1: Color,
    color2: Color,
    color3: Color,
    color4: Color,
    color5: Color,
    color6: Color,
    color7: Color,
    isLight: Boolean
) {
    var navBar by mutableStateOf(navBar, structuralEqualityPolicy())
        internal set

    var tabSelected by mutableStateOf(tabSelected, structuralEqualityPolicy())
        internal set

    var tabDefault by mutableStateOf(tabDefault, structuralEqualityPolicy())
        internal set

    var headline by mutableStateOf(headline, structuralEqualityPolicy())
        internal set

    var emphasize by mutableStateOf(emphasize, structuralEqualityPolicy())
        internal set

    var body1 by mutableStateOf(body1, structuralEqualityPolicy())
        internal set

    var body2 by mutableStateOf(body2, structuralEqualityPolicy())
        internal set

    var footnote by mutableStateOf(footnote, structuralEqualityPolicy())
        internal set

    var background by mutableStateOf(background, structuralEqualityPolicy())
        internal set

    var white by mutableStateOf(white, structuralEqualityPolicy())
        internal set

    var black by mutableStateOf(black, structuralEqualityPolicy())
        internal set

    var default by mutableStateOf(default, structuralEqualityPolicy())
        internal set

    var color by mutableStateOf(color, structuralEqualityPolicy())
        internal set

    var color1 by mutableStateOf(color1, structuralEqualityPolicy())
        internal set

    var color2 by mutableStateOf(color2, structuralEqualityPolicy())
        internal set

    var color3 by mutableStateOf(color3, structuralEqualityPolicy())
        internal set

    var color4 by mutableStateOf(color4, structuralEqualityPolicy())
        internal set

    var color5 by mutableStateOf(color5, structuralEqualityPolicy())
        internal set

    var color6 by mutableStateOf(color6, structuralEqualityPolicy())
        internal set

    var color7 by mutableStateOf(color7, structuralEqualityPolicy())
        internal set

    var isLight by mutableStateOf(isLight, structuralEqualityPolicy())
        internal set

    fun copy(): LfColors = LfColors(
        navBar,
        tabSelected,
        tabDefault,
        headline,
        emphasize,
        body1,
        body2,
        footnote,
        background,
        white,
        black,
        default,
        color,
        color1,
        color2,
        color3,
        color4,
        color5,
        color6,
        color7,
        isLight
    )

    fun update(colors: LfColors) {
        this.navBar = colors.navBar
        this.tabSelected = colors.tabSelected
        this.tabDefault = colors.tabDefault
        this.headline = colors.headline
        this.emphasize = colors.emphasize
        this.body1 = colors.body1
        this.body2 = colors.body2
        this.footnote = colors.footnote
        this.background = colors.background
        this.white = colors.white
        this.black = colors.black
        this.default = colors.default
        this.color = colors.color
        this.color1 = colors.color1
        this.color2 = colors.color2
        this.color3 = colors.color3
        this.color4 = colors.color4
        this.color5 = colors.color5
        this.color6 = colors.color6
        this.color7 = colors.color7
    }

    override fun toString(): String {
        return "Colors(" +
                "navBar=$navBar, " +
                "tabSelected=$tabSelected, " +
                "tabDefault=$tabDefault, " +
                "headline=$headline, " +
                "emphasize=$emphasize, " +
                "body1=$body1, " +
                "body2=$body2, " +
                "footnote=$footnote, " +
                "background=$background, " +
                "white=$white, " +
                "black=$black, " +
                "default=$default, " +
                "color=$color, " +
                "color1=$color1, " +
                "color2=$color2, " +
                "color3=$color3, " +
                "color4=$color4, " +
                "color5=$color5, " +
                "color6=$color6, " +
                "color7=$color7, " +
                "isLight=$isLight" +
                ")"
    }

}
