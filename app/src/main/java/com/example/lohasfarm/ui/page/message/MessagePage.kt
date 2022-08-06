package com.example.lohasfarm.ui.page.message


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.lohasfarm.R
import com.example.lohasfarm.logic.viewModel.MessagePageViewModel
import com.example.lohasfarm.ui.main.nav.Actions
import com.example.lohasfarm.ui.theme.LOHASFarmTheme

@Composable
fun MessagePage(actions: Actions, viewModel: MessagePageViewModel, bottomPadding: Dp) {
    val messageBoxState by viewModel.messageBoxState.observeAsState()
    val sequenceInfoState by viewModel.sequenceInfoState.observeAsState()

    Scaffold(backgroundColor = LOHASFarmTheme.colors.background,
        topBar = {
            Column(
                modifier = Modifier
                    .systemBarsPadding()
                    .height(38.48.dp)
                    .fillMaxWidth()
                    .background(LOHASFarmTheme.colors.white)
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
                    Text(text = "农场通知", color = if (messageBoxState!!) {
                        LOHASFarmTheme.colors.tabSelected
                    } else {
                        LOHASFarmTheme.colors.tabDefault
                    }, style = MaterialTheme.typography.h3,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            if (!messageBoxState!!) {
                                viewModel.changeMessageBoxState()
                            }
                        })

                    Text(text = "系统公告", color = if (!messageBoxState!!) {
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
                                if (messageBoxState!!) {
                                    viewModel.changeMessageBoxState()
                                }
                            })
                }

                Box(
                    modifier = Modifier
                        .padding(
                            if (messageBoxState!!) {
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
            if (messageBoxState!!) {
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, bottomPadding)
                    .background(LOHASFarmTheme.colors.background)){
                    items(items =
                    if (messageBoxState!!) {
                        sequenceInfoState!!
                    } else {
                        sequenceInfoState!!
                    }, key = {data -> data.sequence_info_uid}) { data ->
                        MessageItem(title = data.sequence_info_title,
                            content = data.sequence_info_content,
                            time = data.sequence_info_date,
                            num = data.sequence_info_num,
                            modifier = Modifier
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) {
                                    actions.toDetailMessagePage(data.sequence_info_uid, data.sequence_info_title)
                                },
                            icon = if (data.sequence_info_type > 0) {
                                R.drawable.ic_propaganda_notice
                            } else {
                                R.drawable.ic_harvest_notice
                            })
                    }
                }
            }
        }
}


@Composable
fun MessageItem(title: String, content: String, time: String, num: Int, modifier: Modifier, icon: Int = R.drawable.ic_harvest_notice) {
    Row(modifier = modifier
        .padding(0.dp, 1.04.dp)
        .height(70.72.dp)
        .fillMaxWidth()
        .background(LOHASFarmTheme.colors.white)
        .padding(20.8.dp, 12.48.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Image(modifier = Modifier
            .padding(0.dp)
            .size(45.76.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            contentScale = ContentScale.Fit)

        Column(modifier = Modifier
            .padding(0.dp)
            .width(262.08.dp)
            .height(251.68.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceAround) {
            Text(text = title,
                color = LOHASFarmTheme.colors.headline,
                style = MaterialTheme.typography.h5)

            Text(text = content,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = LOHASFarmTheme.colors.body1,
                style = MaterialTheme.typography.body1)
        }

        Column(modifier = Modifier
            .padding(0.dp)
            .height(39.52.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End) {
                Text(text = time,
                    color = LOHASFarmTheme.colors.headline,
                    style = MaterialTheme.typography.caption)

                Box(modifier = Modifier
                    .padding(0.dp)
                    .size(14.56.dp)
                    .shadow(0.dp, RoundedCornerShape(7.28.dp), clip = true)
                    .background(
                        if (num > 0) {
                            Color(0xFFFF0000)
                        } else {
                            Color.Transparent
                        }
                    ),
                    contentAlignment = Alignment.Center) {
                        if (num > 0) {
                            Text(text = num.toString(),
                                color = LOHASFarmTheme.colors.white,
                                style = MaterialTheme.typography.caption,
                                textAlign = TextAlign.Center)
                        }
                }
        }
    }
}