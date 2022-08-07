package com.chenqianhe.lohasfarm.ui.main

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.chenqianhe.lohasfarm.logic.viewModel.*
import com.chenqianhe.lohasfarm.ui.main.nav.Actions
import com.chenqianhe.lohasfarm.ui.main.nav.Tabs
import com.chenqianhe.lohasfarm.ui.page.activity.ActivityPage
import com.chenqianhe.lohasfarm.ui.page.farm.FarmPage
import com.chenqianhe.lohasfarm.ui.page.message.MessagePage
import com.chenqianhe.lohasfarm.ui.page.mine.MinePage
import com.chenqianhe.lohasfarm.ui.theme.LOHASFarmTheme
import java.util.*

private const val TAG = "MainPage"

@Composable
fun MainPage(actions: Actions) {
    val viewModel: HomeViewModel = hiltViewModel()
    val activityModel: ActivityPageViewModel = hiltViewModel()
    val messageModel: MessagePageViewModel = hiltViewModel()
    val mineModel: MinePageViewModel = hiltViewModel()
    val position by viewModel.position.observeAsState()
    val tabs = Tabs.values()

    val farmPageViewModel: FarmPageViewModel = hiltViewModel()

    Scaffold(
        backgroundColor = LOHASFarmTheme.colors.background,
        bottomBar = {
            BottomNavigation {
                tabs.forEach { tab ->
                    BottomNavigationItem(
                        modifier = Modifier.background(LOHASFarmTheme.colors.white),
                        icon = {
                            val painter: Painter = if (tab == position) {
                                painterResource(tab.selectIcon)
                            } else {
                                painterResource(tab.icon)
                            }
                            Icon(painter, contentDescription = null,  tint= Color.Unspecified)
                        },
                        label = { Text(stringResource(tab.title).uppercase(Locale.ROOT),
                            color = if (tab == position) {
                                LOHASFarmTheme.colors.color
                            } else {
                                LOHASFarmTheme.colors.tabDefault
                            },
                            style = if (tab == position) {
                                MaterialTheme.typography.overline
                            } else {
                                MaterialTheme.typography.h4
                            }) },
                        selected = tab == position,
                        onClick = {
                            viewModel.onPositionChanged(tab)
                        },
                        alwaysShowLabel = true
                    )
                }
            }
        }) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            Crossfade(targetState = position) { screen ->
                when (screen) {
                    Tabs.FARM_PAGE -> {
                        farmPageViewModel.updateLandInfo()
                        farmPageViewModel.updatePlantIntroInfo()
                        FarmPage(actions, farmPageViewModel)
                    }
                    Tabs.ACTIVITY_PAGE -> {
                        activityModel.getFoodActivityInfo()
                        activityModel.getFarmActivityInfo()
                        ActivityPage(actions, activityModel, innerPadding.calculateBottomPadding())
                    }
                    Tabs.MESSAGE_PAGE -> {
                        messageModel.getSequenceInfoData()
                        MessagePage(actions, messageModel, innerPadding.calculateBottomPadding())
                    }
                    Tabs.MINE_PAGE -> {
                        mineModel.getUserData()
                        MinePage(actions, mineModel, innerPadding.calculateBottomPadding())
                    }
                    else -> {
                        Log.e(TAG, "页面显示错误")
                    }
                }
            }
        }
}
