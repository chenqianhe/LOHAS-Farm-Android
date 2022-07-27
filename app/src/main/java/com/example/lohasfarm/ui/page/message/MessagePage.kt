package com.example.lohasfarm.ui.page.message

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.lohasfarm.ui.theme.LOHASFarmTheme

@Composable
fun MessagePage() {
    Text(text = "Hello MessagePage!",
        color = LOHASFarmTheme.colors.color1,
        style = MaterialTheme.typography.h1)
}