package com.chenqianhe.lohasfarm.ui.page.farm


import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.chenqianhe.lohasfarm.R
import com.chenqianhe.lohasfarm.logic.viewModel.MineLandPageViewModel
import com.chenqianhe.lohasfarm.ui.main.nav.Actions
import com.chenqianhe.lohasfarm.ui.theme.LOHASFarmTheme


@Composable
fun MineLandPage(actions: Actions,
                 landUid: String,
                 name: String,
                 landPlantedArea: Int,
                 landTotalArea: Int,
                 profilePhotoUrl: String,
                 landLeaseTerm: String) {
    val viewModel: MineLandPageViewModel = hiltViewModel()
    viewModel.getPlantData()
    viewModel.getPlantAddableData()
    val isFinished by viewModel.finished.observeAsState(false)
    val addingState by viewModel.addState.observeAsState(false)
    val plantInfoState by viewModel.plantInfoState.observeAsState()
    val plantAddableInfoState by viewModel.plantAddableInfoState.observeAsState()
    val plantAddedNumState by viewModel.plantAddedInfoState.observeAsState()

    Scaffold(modifier = Modifier
        .systemBarsPadding()
        .background(LOHASFarmTheme.colors.white),
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .background(LOHASFarmTheme.colors.white)
                    .padding(0.dp),
                contentColor = LOHASFarmTheme.colors.black,
                title = {
                    Column(modifier = Modifier
                        .padding(75.dp, 0.dp)
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            if (addingState) {
                                viewModel.disableAdd()
                            }
                        },
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center) {
                        Text(modifier = Modifier
                            .padding(0.dp)
                            .width(104.dp),
                            text = "${name}的农场",
                            color = LOHASFarmTheme.colors.navBar,
                            style = MaterialTheme.typography.h1,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    IconButton(modifier = Modifier
                        .padding(0.dp)
                        .background(LOHASFarmTheme.colors.white),
                        onClick = { actions.upPress() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                backgroundColor = LOHASFarmTheme.colors.white,
                elevation = 0.dp)
        }) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)

        Box(modifier = Modifier.padding(0.dp)) {
            Column(modifier = Modifier
                .padding(0.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    if (addingState) {
                        viewModel.disableAdd()
                    }
                },
                horizontalAlignment = Alignment.CenterHorizontally) {

                Row(modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    Image(modifier = Modifier
                        .padding(0.dp)
                        .size(49.92.dp)
                        .shadow(0.dp, RoundedCornerShape(24.96.dp), clip = true),
                        painter = rememberAsyncImagePainter(model = profilePhotoUrl.replace("斜杠", "/")),
                        contentDescription = null)

                    Column(modifier = Modifier
                        .padding(8.32.dp, 0.dp, 0.dp, 0.dp)
                        .height(48.88.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "${name}的农场", color = LOHASFarmTheme.colors.headline, style = MaterialTheme.typography.h5)

                        Text(text = "土地已种${landPlantedArea}㎡  剩余${landTotalArea-landPlantedArea}㎡  作物${if (isFinished) {
                            plantInfoState!!.size
                        } else {
                            ""
                        }}种", color = LOHASFarmTheme.colors.body2, style = MaterialTheme.typography.body2)
                    }
                }

                if (isFinished) {
                    GrowthStage(plantData = plantInfoState!!, Modifier.padding(20.8.dp, 12.48.dp))

                    Column(modifier = Modifier
                        .padding(15.dp, 0.dp)
                        .fillMaxWidth()
                    ) {
                        Image(modifier = Modifier
                            .size(68.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                if (!addingState) {
                                    viewModel.enableAdd()
                                }
                            },
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = null,
                            contentScale = ContentScale.Fit)
                    }
                    
                    Row(modifier = Modifier
                        .padding(28.6.dp, 66.56.dp)
                        .width(332.8.dp)
                        .height(71.76.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier
                            .padding(0.dp)
                            .fillMaxHeight()
                            .width(49.92.dp)) {
                            Image(modifier = Modifier
                                .padding(0.dp)
                                .size(49.92.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) {
                                    actions.toCropManagementPage()
                                },
                                painter = painterResource(id = R.drawable.ic_managing_crops),
                                contentDescription = null,
                                contentScale = ContentScale.Fit)
                            Text(text = "作物管理", color = LOHASFarmTheme.colors.body1, style = MaterialTheme.typography.body2)
                        }

                        Column(modifier = Modifier
                            .padding(0.dp)
                            .fillMaxHeight()
                            .width(49.92.dp).clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                actions.toWebPage(
                                    "https://m.baidu.com".replace("/", "斜杠"),
                                    "农事预约"
                                )
                            }) {
                            Image(modifier = Modifier
                                .padding(0.dp)
                                .size(49.92.dp),
                                painter = painterResource(id = R.drawable.ic_agricultural_appointment),
                                contentDescription = null,
                                contentScale = ContentScale.Fit)
                            Text(text = "农事预约", color = LOHASFarmTheme.colors.body1, style = MaterialTheme.typography.body2)
                        }

                        Column(modifier = Modifier
                            .padding(0.dp)
                            .fillMaxHeight()
                            .width(49.92.dp)) {
                            Image(modifier = Modifier
                                .padding(0.dp)
                                .size(49.92.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) {
                                    actions.toTutorPage()
                                },
                                painter = painterResource(id = R.drawable.ic_planting_tutorial),
                                contentDescription = null,
                                contentScale = ContentScale.Fit)
                            Text(text = "种植教程", color = LOHASFarmTheme.colors.body1, style = MaterialTheme.typography.body2)
                        }

                        Column(modifier = Modifier
                            .padding(0.dp)
                            .fillMaxHeight()
                            .width(49.92.dp)) {
                            Image(modifier = Modifier
                                .padding(0.dp)
                                .size(49.92.dp),
                                painter = painterResource(id = R.drawable.ic_my_warehouse),
                                contentDescription = null,
                                contentScale = ContentScale.Fit)
                            Text(text = "我的仓库", color = LOHASFarmTheme.colors.body1, style = MaterialTheme.typography.body2)
                        }

                        Column(modifier = Modifier
                            .padding(0.dp)
                            .fillMaxHeight()
                            .width(49.92.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                actions.toMineLandInfo(
                                    name,
                                    landLeaseTerm,
                                    "已种${landPlantedArea}㎡ \\ 剩余${landTotalArea - landPlantedArea}㎡"
                                )
                            }) {
                            Image(modifier = Modifier
                                .padding(0.dp)
                                .size(49.92.dp),
                                painter = painterResource(id = R.drawable.ic_my_plot),
                                contentDescription = null,
                                contentScale = ContentScale.Fit)
                            Text(text = "我的地块", color = LOHASFarmTheme.colors.body1, style = MaterialTheme.typography.body2)
                        }
                    }
                }
            }

            if (addingState) {
                Column(
                    modifier = Modifier
                        .padding(0.dp, 42.64.dp, 0.dp, 0.dp)
                        .shadow(4.16.dp, RoundedCornerShape(16.64.dp, 16.64.dp, 0.dp, 0.dp))
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(LOHASFarmTheme.colors.white)
                ) {
                    Text(modifier = Modifier.padding(20.8.dp, 20.8.dp, 0.dp, 10.4.dp),
                        text = "添加作物",
                        color = LOHASFarmTheme.colors.navBar,
                        style = MaterialTheme.typography.h5)

                    LazyColumn(modifier = Modifier
                        .padding(0.dp)
                        .fillMaxWidth()
                        .height(525.dp)
                        .padding(20.8.dp, 0.dp))  {
                            items(items =  plantAddableInfoState!!, key = {data -> data.plantInfo_uid}) { data ->
                                Row(modifier = Modifier
                                    .padding(0.dp, 10.4.dp)
                                    .fillMaxWidth()
                                    .height(83.2.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically) {

                                    Row(modifier = Modifier
                                        .padding(0.dp)
                                        .fillMaxHeight()) {
                                        Image(modifier = Modifier
                                            .size(83.2.dp)
                                            .shadow(0.dp, RoundedCornerShape(8.32.dp), clip = true),
                                            painter = rememberAsyncImagePainter(model = data.plantInfo_photo_url),
                                            contentDescription = null,
                                            contentScale = ContentScale.Fit
                                        )

                                        Column(modifier = Modifier
                                            .padding(12.48.dp, 0.dp, 0.dp, 0.dp)
                                            .fillMaxHeight(),
                                            horizontalAlignment = Alignment.Start,
                                            verticalArrangement = Arrangement.SpaceBetween) {
                                            Text(modifier = Modifier
                                                .padding(0.dp)
                                                .height(22.88.dp),
                                                text = data.plantInfo_name,
                                                color = LOHASFarmTheme.colors.headline,
                                                style = MaterialTheme.typography.h5)

                                            Column(modifier = Modifier
                                                .padding(0.dp)
                                                .height(43.68.dp),
                                                horizontalAlignment = Alignment.Start,
                                                verticalArrangement = Arrangement.SpaceBetween) {
                                                Text(text = "生长期：${data.plantInfo_total_day}",
                                                    color = LOHASFarmTheme.colors.footnote,
                                                    style = MaterialTheme.typography.body1)

                                                Text(text = "品种：${data.plantInfo_type}",
                                                    color = LOHASFarmTheme.colors.footnote,
                                                    style = MaterialTheme.typography.body1)
                                            }
                                        }
                                    }

                                    Column(modifier = Modifier
                                        .padding(0.dp)
                                        .fillMaxHeight(),
                                    horizontalAlignment = Alignment.End,
                                    verticalArrangement = Arrangement.Bottom) {
                                        Row(modifier = Modifier
                                            .padding(0.dp)
                                            .width(104.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = if (plantAddedNumState!![data.plantInfo_uid]!! > 0) {
                                            Arrangement.SpaceBetween
                                        } else {
                                            Arrangement.End
                                        }) {
                                            if (plantAddedNumState!![data.plantInfo_uid]!! > 0) {
                                                Image(painter = painterResource(id = R.drawable.ic_mius),
                                                    contentDescription = null,
                                                    modifier = Modifier.clickable(
                                                        interactionSource = remember { MutableInteractionSource() },
                                                        indication = null
                                                    ) {
                                                        viewModel.changePlantNum(data.plantInfo_uid, 0)
                                                    })

                                                Text(text = "${plantAddedNumState!![data.plantInfo_uid]}㎡",
                                                    color = LOHASFarmTheme.colors.body1,
                                                    style = MaterialTheme.typography.body1)
                                            }

                                            Image(painter = painterResource(id = R.drawable.ic_add_rec),
                                                contentDescription = null,
                                                modifier = Modifier.clickable(
                                                    interactionSource = remember { MutableInteractionSource() },
                                                    indication = null
                                                ) {
                                                    viewModel.changePlantNum(data.plantInfo_uid, 1)
                                                })
                                        }
                                    }


                                }
                            }
                    }

                    Row(modifier = Modifier
                        .padding(23.4.dp, 20.8.dp, 23.4.dp, 0.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                        Button(modifier = Modifier
                            .padding(0.dp)
                            .height(41.6.dp),
                            elevation = ButtonDefaults.elevation(0.dp),
                            colors = ButtonDefaults.buttonColors(LOHASFarmTheme.colors.white),
                            shape = RoundedCornerShape(20.8.dp),
                            border = BorderStroke(1.04.dp, LOHASFarmTheme.colors.color),
                            contentPadding = PaddingValues(0.dp),
                            onClick = { viewModel.clearAll() }) {
                            Text(modifier = Modifier.padding(0.dp, 8.5.dp)
                                .fillMaxHeight()
                                .width(124.8.dp),
                                text = "重置",
                                color = LOHASFarmTheme.colors.color,
                                style = MaterialTheme.typography.button,
                                textAlign = TextAlign.Center)
                        }

                        Button(modifier = Modifier
                            .padding(0.dp)
                            .height(41.6.dp),
                            elevation = ButtonDefaults.elevation(0.dp),
                            colors = ButtonDefaults.buttonColors(LOHASFarmTheme.colors.color),
                            shape = RoundedCornerShape(20.8.dp),
                            contentPadding = PaddingValues(0.dp),
                            onClick = { viewModel.uploadAddedPlant() }) {
                            Text(modifier = Modifier.padding(0.dp, 8.5.dp)
                                .fillMaxHeight()
                                .width(197.6.dp),
                                text = "确认添加",
                                color = LOHASFarmTheme.colors.white,
                                style = MaterialTheme.typography.button,
                                textAlign = TextAlign.Center)
                        }

                    }
                }
            }
        }


    }

}
