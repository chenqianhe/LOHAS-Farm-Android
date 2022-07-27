package com.example.lohasfarm.ui.page.farm

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.lohasfarm.ui.theme.LOHASFarmTheme

@Composable
fun FarmPage() {
    Text(text = "Hello FarmPage!",
        color = LOHASFarmTheme.colors.color1,
        style = MaterialTheme.typography.h1)
}
