package com.chenqianhe.lohasfarm.ui.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.chenqianhe.lohasfarm.R

fun getPlantIcon(name: String): Int {
    return when(name) {
        "胡萝卜" -> R.drawable.ic_hu_luo_bo
        "茄子" -> R.drawable.ic_qie_zi
        "西红柿" -> R.drawable.ic_xi_hong_shi
        "白菜" -> R.drawable.ic_bai_cai
        "西兰花" -> R.drawable.ic_xi_lan_hua
        "辣椒" -> R.drawable.ic_la_jiao
        else -> R.drawable.ic_bai_cai
    }
}


fun getBarLength(name: String): Dp {
    return when(name) {
        "果期" -> 335.dp
        "花期" -> 260.dp
        "苗期" -> 160.dp
        "芽期", "发芽" -> 91.52.dp
        else -> 91.52.dp
    }
}

fun getBarColor(name: String): Color {
    return when(name) {
        "胡萝卜" -> Color(0xFF008C46)
        "茄子" -> Color(0xFF008C46)
        "西红柿" -> Color(0xFF69B244)
        "白菜" -> Color(0xFFF27230)
        "西兰花" -> Color(0xFFCFE567)
        "辣椒" -> Color(0xFF69B244)
        else -> Color(0xFFF27230)
    }
}