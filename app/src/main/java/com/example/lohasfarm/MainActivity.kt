package com.example.lohasfarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lohasfarm.ui.main.NavGraph
import com.example.lohasfarm.ui.theme.LOHASFarmTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LOHASFarmTheme {
                NavGraph()
            }
        }
    }
}
