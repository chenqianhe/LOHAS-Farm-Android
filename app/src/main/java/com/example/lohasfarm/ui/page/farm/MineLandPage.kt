package com.example.lohasfarm.ui.page.farm

import android.view.RoundedCorner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
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
import com.example.lohasfarm.R
import com.example.lohasfarm.logic.viewModel.MineLandPageViewModel
import com.example.lohasfarm.ui.main.nav.Actions
import com.example.lohasfarm.ui.theme.LOHASFarmTheme


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
    val isFinished by viewModel.finished.observeAsState(false)
    val addingState by viewModel.addState.observeAsState(false)
    val plantInfoState by viewModel.plantInfoState.observeAsState()

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
                        .fillMaxWidth(),
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
            Column(modifier = Modifier.padding(0.dp),
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
                            .size(68.dp),
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
                                .size(49.92.dp),
                                painter = painterResource(id = R.drawable.ic_managing_crops),
                                contentDescription = null,
                                contentScale = ContentScale.Fit)
                            Text(text = "作物管理", color = LOHASFarmTheme.colors.body1, style = MaterialTheme.typography.body2)
                        }

                        Column(modifier = Modifier
                            .padding(0.dp)
                            .fillMaxHeight()
                            .width(49.92.dp)) {
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
                                .size(49.92.dp),
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
                                actions.toMineLandInfo(name, landLeaseTerm, "已种${landPlantedArea}㎡ \\ 剩余${landTotalArea-landPlantedArea}㎡")
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
        }


    }

}