package com.example.lohasfarm.ui.page.activity

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lohasfarm.logic.viewModel.ActivityPageViewModel
import com.example.lohasfarm.logic.viewModel.FarmPageViewModel
import com.example.lohasfarm.ui.main.nav.Actions
import com.example.lohasfarm.ui.theme.LOHASFarmTheme

@Composable
fun ActivityPage(actions: Actions, activityPageViewModel: ActivityPageViewModel) {
    val activityItemState by activityPageViewModel.activityItemState.observeAsState()

    Scaffold(backgroundColor = LOHASFarmTheme.colors.white,
    topBar = {
        Row(modifier = Modifier
            .systemBarsPadding()
            .padding(22.88.dp, 0.dp, 0.dp, 0.dp)
            .height(91.52.dp)
            .fillMaxWidth()) {
            Text(text = "农副产品", color = if (activityItemState!!) {
                LOHASFarmTheme.colors.tabSelected
            } else {
                LOHASFarmTheme.colors.tabDefault
            }, style = MaterialTheme.typography.h3)
        }
    }) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Hello ActivityPage!",
                color = LOHASFarmTheme.colors.color1,
                style = MaterialTheme.typography.h1)
        }
    }
}