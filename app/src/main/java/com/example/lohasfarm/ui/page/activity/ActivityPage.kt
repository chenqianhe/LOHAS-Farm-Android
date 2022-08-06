package com.example.lohasfarm.ui.page.activity

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.lohasfarm.logic.viewModel.ActivityPageViewModel
import com.example.lohasfarm.ui.main.nav.Actions
import com.example.lohasfarm.ui.theme.LOHASFarmTheme

@Composable
fun ActivityPage(actions: Actions, activityPageViewModel: ActivityPageViewModel, bottomPadding: Dp) {
    val activityItemState by activityPageViewModel.activityItemState.observeAsState()
    val foodActivityState by activityPageViewModel.foodActivityInfoState.observeAsState()
    val farmActivityState by activityPageViewModel.farmActivityInfoState.observeAsState()

    Scaffold(backgroundColor = LOHASFarmTheme.colors.white,
    topBar = {
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .height(38.48.dp)
                .fillMaxWidth()
                .padding(22.88.dp, 0.dp, 0.dp, 0.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                modifier = Modifier
                    .padding(0.dp)
                    .height(28.08.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "农副食品", color = if (activityItemState!!) {
                    LOHASFarmTheme.colors.tabSelected
                } else {
                    LOHASFarmTheme.colors.tabDefault
                }, style = MaterialTheme.typography.h3,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        if (!activityItemState!!) {
                            activityPageViewModel.changeActivityItemState()
                        }
                    })

                Text(text = "农场活动", color = if (!activityItemState!!) {
                    LOHASFarmTheme.colors.tabSelected
                } else {
                    LOHASFarmTheme.colors.tabDefault
                }, style = MaterialTheme.typography.h3,
                    modifier = Modifier
                        .padding(24.96.dp, 0.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            if (activityItemState!!) {
                                activityPageViewModel.changeActivityItemState()
                            }
                        })
            }

            Box(
                modifier = Modifier
                    .padding(
                        if (activityItemState!!) {
                            31.2.dp
                        } else {
                            139.dp
                        },
                        0.dp, 0.dp, 0.dp
                    )
                    .width(20.8.dp)
                    .height(2.dp)
                    .background(LOHASFarmTheme.colors.tabSelected))
        }
    }) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)

        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .padding(20.8.dp, 0.dp, 20.8.dp, bottomPadding)) {
            items(items =
            if (activityItemState!!) {
                foodActivityState!!
            } else {
               farmActivityState!!
           }, key = {data -> data.activity_uid}) { data ->
                ActivityItem(title = data.activity_title,
                    url = data.activity_photo_url,
                    date = data.activity_date,
                    modifier = Modifier.clickable {
                        actions.toWebPage(data.activity_final_url.replace("https://", ""),
                            if (activityItemState!!) {
                                "农副食品"
                            } else {
                                "农场活动"
                            })
                    })
            }
        }
    }
}


@Composable
fun ActivityItem(title: String, url: String, date: String, modifier: Modifier) {
    Row(modifier = modifier
        .padding(0.dp, 8.32.dp)
        .height(83.2.dp)
        .width(348.4.dp),
    horizontalArrangement = Arrangement.SpaceAround) {
        Image(modifier = Modifier
            .padding(0.dp)
            .size(83.2.dp),
            painter = rememberAsyncImagePainter(model = url),
            contentDescription = null,
            contentScale = ContentScale.Fit)

        Column(modifier = Modifier
            .padding(0.dp)
            .width(252.72.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = LOHASFarmTheme.colors.headline,
                style = MaterialTheme.typography.h5
            )

            Text(text = date,
                color = LOHASFarmTheme.colors.footnote,
                style = MaterialTheme.typography.body2)
        }
    }
}

