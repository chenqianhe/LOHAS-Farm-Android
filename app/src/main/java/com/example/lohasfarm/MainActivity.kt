package com.example.lohasfarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lohasfarm.ui.theme.LOHASFarmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LOHASFarmTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = LOHASFarmTheme.colors.color
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!",
        color = LOHASFarmTheme.colors.color1,
        style = MaterialTheme.typography.h1)
    Text(text = "Hello2 $name!",
        color = LOHASFarmTheme.colors.color2,
        style = MaterialTheme.typography.caption)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LOHASFarmTheme {
        Greeting("Android")
    }
}