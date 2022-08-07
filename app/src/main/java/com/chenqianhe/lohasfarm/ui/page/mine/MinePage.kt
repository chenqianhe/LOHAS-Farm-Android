package com.chenqianhe.lohasfarm.ui.page.mine

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.chenqianhe.lohasfarm.R
import com.chenqianhe.lohasfarm.logic.utils.LfState
import com.chenqianhe.lohasfarm.logic.viewModel.MinePageViewModel
import com.chenqianhe.lohasfarm.ui.main.nav.Actions
import com.chenqianhe.lohasfarm.ui.theme.LOHASFarmTheme


@Composable
fun MinePage(actions: Actions, viewModel: MinePageViewModel, bottomPadding: Dp) {
    val userInfoState by viewModel.userInfoState.observeAsState()
    val finishState by viewModel.finished.observeAsState(false)

    Scaffold(modifier = Modifier
        .systemBarsPadding()
        .background(LOHASFarmTheme.colors.background),
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .background(LOHASFarmTheme.colors.white)
                    .padding(0.dp),
                contentColor = LOHASFarmTheme.colors.black,
                title = {
                    Column(modifier = Modifier
                        .padding(130.dp, 0.dp, 0.dp, 0.dp)
                        .fillMaxHeight()
                        .fillMaxWidth(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center) {
                        Text(modifier = Modifier
                            .padding(0.dp)
                            .width(104.dp),
                            text = "乐活农场",
                            color = LOHASFarmTheme.colors.navBar,
                            style = MaterialTheme.typography.h1,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                backgroundColor = LOHASFarmTheme.colors.white,
                elevation = 0.dp)
        },
        backgroundColor = LOHASFarmTheme.colors.background) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        Column(modifier = Modifier.padding(0.dp)) {
            Box(modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter) {
                Image(modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.mine_page_background),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth)
                Column(modifier = Modifier
                    .padding(0.dp, 20.8.dp)
                    .height(93.6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween) {
                    if (finishState) {
                        Image(modifier = Modifier
                            .padding(0.dp)
                            .size(62.4.dp)
                            .shadow(
                                elevation = 0.dp,
                                shape = RoundedCornerShape(31.2.dp),
                                clip = true
                            )
                            .border(2.08.dp, Color.White, CircleShape),
                            painter = rememberAsyncImagePainter(model = userInfoState!!.profile_photo),
                            contentDescription = null,
                            contentScale = ContentScale.Fit)

                        Text(text = userInfoState!!.name,
                            color = LOHASFarmTheme.colors.white,
                            style = MaterialTheme.typography.h5)
                    }

                    
                }
            }
            if (finishState) {
                LazyColumn(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, bottomPadding)) {
                    item {
                        Row(modifier = Modifier
                            .padding(0.dp)
                            .fillMaxWidth()
                            .height(45.76.dp)
                            .background(LOHASFarmTheme.colors.white)
                            .padding(20.8.dp, 12.48.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                actions.toPersonalInfo(userInfoState!!.uid,
                                    userInfoState!!.ugid,
                                    userInfoState!!.name,
                                    userInfoState!!.profile_photo.replace("/", "斜杠"))
                            },
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.Bottom) {
                            Image(painter = painterResource(id = R.drawable.ic_personal_information),
                                contentDescription = null,
                                modifier = Modifier.size(20.8.dp),
                                contentScale = ContentScale.Fit)
                            Text(text = "个人信息",
                                color = LOHASFarmTheme.colors.headline,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier
                                    .padding(8.32.dp, 0.dp)
                                    .height(20.8.dp),
                                textAlign = TextAlign.Center)
                        }
                    }

                    item {
                        Row(modifier = Modifier
                            .padding(0.dp)
                            .fillMaxWidth()
                            .height(45.76.dp)
                            .background(LOHASFarmTheme.colors.white)
                            .padding(20.8.dp, 12.48.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.Bottom) {
                            Image(painter = painterResource(id = R.drawable.ic_farm_information),
                                contentDescription = null,
                                modifier = Modifier.size(20.8.dp),
                                contentScale = ContentScale.Fit)
                            Text(text = "农场信息",
                                color = LOHASFarmTheme.colors.headline,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier
                                    .padding(8.32.dp, 0.dp)
                                    .height(20.8.dp),
                                textAlign = TextAlign.Center)
                        }
                    }

                    item {
                        Row(modifier = Modifier
                            .padding(0.dp)
                            .fillMaxWidth()
                            .height(45.76.dp)
                            .background(LOHASFarmTheme.colors.white)
                            .padding(20.8.dp, 12.48.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.Bottom) {
                            Image(painter = painterResource(id = R.drawable.ic_group),
                                contentDescription = null,
                                modifier = Modifier.size(20.8.dp),
                                contentScale = ContentScale.Fit)
                            Text(text = "生产队群组",
                                color = LOHASFarmTheme.colors.headline,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier
                                    .padding(8.32.dp, 0.dp)
                                    .height(20.8.dp),
                                textAlign = TextAlign.Center)
                        }
                    }

                    item {
                        Row(modifier = Modifier
                            .padding(0.dp, 4.16.dp)
                            .fillMaxWidth()
                            .height(45.76.dp)
                            .background(LOHASFarmTheme.colors.white)
                            .padding(20.8.dp, 12.48.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.Bottom) {
                            Image(painter = painterResource(id = R.drawable.ic_growth_record),
                                contentDescription = null,
                                modifier = Modifier.size(20.8.dp),
                                contentScale = ContentScale.Fit)
                            Text(text = "农夫成长记录",
                                color = LOHASFarmTheme.colors.headline,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier
                                    .padding(8.32.dp, 0.dp)
                                    .height(20.8.dp),
                                textAlign = TextAlign.Center)
                        }
                    }

                    item {
                        Row(modifier = Modifier
                            .padding(0.dp)
                            .fillMaxWidth()
                            .height(45.76.dp)
                            .background(LOHASFarmTheme.colors.white)
                            .padding(20.8.dp, 12.48.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.Bottom) {
                            Image(painter = painterResource(id = R.drawable.ic_user_agreement),
                                contentDescription = null,
                                modifier = Modifier.size(20.8.dp),
                                contentScale = ContentScale.Fit)
                            Text(text = "用户协议",
                                color = LOHASFarmTheme.colors.headline,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier
                                    .padding(8.32.dp, 0.dp)
                                    .height(20.8.dp),
                                textAlign = TextAlign.Center)
                        }
                    }

                    item {
                        Row(modifier = Modifier
                            .padding(0.dp)
                            .fillMaxWidth()
                            .height(45.76.dp)
                            .background(LOHASFarmTheme.colors.white)
                            .padding(20.8.dp, 12.48.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.Bottom) {
                            Image(painter = painterResource(id = R.drawable.ic_privacy_policy),
                                contentDescription = null,
                                modifier = Modifier.size(20.8.dp),
                                contentScale = ContentScale.Fit)
                            Text(text = "隐私政策",
                                color = LOHASFarmTheme.colors.headline,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier
                                    .padding(8.32.dp, 0.dp)
                                    .height(20.8.dp),
                                textAlign = TextAlign.Center)
                        }
                    }

                    item {
                        Row(modifier = Modifier
                            .padding(0.dp)
                            .fillMaxWidth()
                            .height(45.76.dp)
                            .background(LOHASFarmTheme.colors.white)
                            .padding(20.8.dp, 12.48.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.Bottom) {
                            Image(painter = painterResource(id = R.drawable.ic_help_and_feedback),
                                contentDescription = null,
                                modifier = Modifier.size(20.8.dp),
                                contentScale = ContentScale.Fit)
                            Text(text = "帮助与反馈",
                                color = LOHASFarmTheme.colors.headline,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier
                                    .padding(8.32.dp, 0.dp)
                                    .height(20.8.dp),
                                textAlign = TextAlign.Center)
                        }
                    }

                    item {
                        Row(modifier = Modifier
                            .padding(0.dp)
                            .fillMaxWidth()
                            .height(45.76.dp)
                            .background(LOHASFarmTheme.colors.white)
                            .padding(20.8.dp, 12.48.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.Bottom) {
                            Image(painter = painterResource(id = R.drawable.ic_about),
                                contentDescription = null,
                                modifier = Modifier.size(20.8.dp),
                                contentScale = ContentScale.Fit)
                            Text(text = "关于",
                                color = LOHASFarmTheme.colors.headline,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier
                                    .padding(8.32.dp, 0.dp)
                                    .height(20.8.dp),
                                textAlign = TextAlign.Center)
                        }
                    }

                    item {
                        Box(modifier = Modifier.fillMaxWidth().background(LOHASFarmTheme.colors.white)) {
                            Button(modifier = Modifier
                                .padding(49.4.dp, 2.48.dp)
                                .fillMaxWidth()
                                .height(41.6.dp),
                                shape = RoundedCornerShape(23.dp),
                                border = BorderStroke(1.04.dp, LOHASFarmTheme.colors.color),
                                contentPadding = PaddingValues(0.dp),
                                elevation = ButtonDefaults.elevation(0.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = LOHASFarmTheme.colors.white),
                                onClick = {
                                    LfState.clearAll()
                                    actions.clearBackStack()
                                    actions.toLoginPage()
                                }) {
                                Text(text = "退出登录",
                                    color = LOHASFarmTheme.colors.color,
                                    style = MaterialTheme.typography.button
                                )
                            }
                        }

                    }
                }
            }
        }
    }

}