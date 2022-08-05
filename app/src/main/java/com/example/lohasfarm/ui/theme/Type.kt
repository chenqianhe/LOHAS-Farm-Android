package com.example.lohasfarm.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.lohasfarm.R


val ALIBABAPUHUITI2 = FontFamily(
    Font(R.font.alibabapuhuiti2_light, FontWeight.Light), // 300
    Font(R.font.alibabapuhuiti2_medium, FontWeight.Medium), // 500
    Font(R.font.alibabapuhuiti2_regular, FontWeight.Normal) // 400
)

const val ReductionRate = 3
const val AdaptationRate = 2.875

fun calSize(RawPx: Number): Double {
    return RawPx.toDouble() * ReductionRate / AdaptationRate
}

// 为了便捷开发使用原本的字体系统代指自己设计的字体系统
val LfLightFontPalette = Typography(
    // nav-bar
    h1 = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Medium,
        fontSize = calSize(20).sp
    ),
    // tab-default
    h2 = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Normal,
        fontSize = calSize(20).sp
    ),
    // tab-highlight
    h3 = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Normal,
        fontSize = calSize(20).sp
    ),
    // tab-bar-default
    h4 = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Normal,
        fontSize = calSize(12).sp
    ),
    // tab-bar-select
    overline = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Medium,
        fontSize = calSize(12).sp
    ),
    // button
    button = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Medium,
        fontSize = calSize(16).sp
    ),
    // headline
    h5 = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Medium,
        fontSize = calSize(16).sp
    ),
    // emphasize
    h6 = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Medium,
        fontSize = calSize(16).sp
    ),
    // body1
    body1 = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Medium,
        fontSize = calSize(14).sp
    ),
    // body2
    body2 = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Normal,
        fontSize = calSize(12).sp
    ),
    // footnote
    caption = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Light,
        fontSize = calSize(10).sp
    )
)

val LfDarkFontPalette = Typography(
    // nav-bar
    h1 = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Medium,
        fontSize = calSize(20).sp
    ),
    // tab-default
    h2 = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Normal,
        fontSize = calSize(20).sp
    ),
    // tab-highlight
    h3 = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Medium,
        fontSize = calSize(20).sp
    ),
    // tab-bar-default
    h4 = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Normal,
        fontSize = calSize(12).sp
    ),
    // tab-bar-select
    overline = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Medium,
        fontSize = calSize(12).sp
    ),
    // button
    button = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Medium,
        fontSize = calSize(16).sp
    ),
    // headline
    h5 = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Medium,
        fontSize = calSize(16).sp
    ),
    // emphasize
    h6 = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Medium,
        fontSize = calSize(16).sp
    ),
    // body1
    body1 = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Medium,
        fontSize = calSize(14).sp
    ),
    // body2
    body2 = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Normal,
        fontSize = calSize(12).sp
    ),
    // footnote
    caption = TextStyle(
        fontFamily = ALIBABAPUHUITI2,
        fontWeight = FontWeight.Light,
        fontSize = calSize(10).sp
    )
)