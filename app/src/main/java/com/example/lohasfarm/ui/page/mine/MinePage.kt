package com.example.lohasfarm.ui.page.mine

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.lohasfarm.ui.theme.LOHASFarmTheme

@Composable
fun MinePage() {
    Text(text = "Hello MainPage!",
        color = LOHASFarmTheme.colors.color1,
        style = MaterialTheme.typography.h1)
}