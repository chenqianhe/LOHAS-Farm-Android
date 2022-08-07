package com.chenqianhe.lohasfarm

import android.os.Bundle
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.chenqianhe.lohasfarm.ui.main.NavGraph
import com.chenqianhe.lohasfarm.ui.theme.LOHASFarmTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        const val DURATION = 3000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSplashScreen()
        setContent {
            LOHASFarmTheme {
                NavGraph()
            }
        }
    }

    /**
     * 实现启动页
     */
    private fun initSplashScreen() {
        val startMillis = SystemClock.uptimeMillis()
        val mSplashScreenView = installSplashScreen()
        mSplashScreenView.setKeepOnScreenCondition {
            SystemClock.uptimeMillis() - startMillis < DURATION
        }
    }
}
