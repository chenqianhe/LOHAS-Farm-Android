package com.example.lohasfarm.ui.page.activity

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.lohasfarm.ui.theme.LOHASFarmTheme

@Composable
fun ActivityPage() {
    Text(text = "Hello ActivityPage!",
        color = LOHASFarmTheme.colors.color1,
        style = MaterialTheme.typography.h1)
}