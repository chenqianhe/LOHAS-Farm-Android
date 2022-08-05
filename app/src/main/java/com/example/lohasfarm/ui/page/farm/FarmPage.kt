package com.example.lohasfarm.ui.page.farm

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.lohasfarm.R
import com.example.lohasfarm.logic.utils.LfState
import com.example.lohasfarm.logic.viewModel.FarmPageViewModel
import com.example.lohasfarm.ui.main.nav.Actions
import com.example.lohasfarm.ui.theme.LOHASFarmTheme


private const val TAG = "FARM_PAGE"

@Composable
fun FarmPage(actions: Actions, farmPageViewModel: FarmPageViewModel) {

    val landInfo by farmPageViewModel.landInfoState.observeAsState()
    val plantIntroInfo by farmPageViewModel.plantIntroInfoState.observeAsState()
    val paddings: Array<Array<Double>> = arrayOf(
        arrayOf(37.44, 119.6), arrayOf(167.44, 119.6), arrayOf(298.48, 119.6),
        arrayOf(37.44, 242.32), arrayOf(163.28, 225.68), arrayOf(298.48, 242.32),
        arrayOf(37.44, 348.4), arrayOf(167.44, 348.4), arrayOf(298.48, 348.4)
    )
    val landName = remember {
        mutableStateOf(LfState.landName)
    }
    Log.i(TAG, plantIntroInfo!!.size.toString())

    Scaffold(
        backgroundColor = LOHASFarmTheme.colors.background){ innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.TopStart,
                modifier = modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_farm_page_background),
                    contentDescription = "farm_page_background",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillBounds)

                landInfo?.forEach {
                    if (it.is_mine) {
                        LandSign(
                            name = it.land_name,
                            url = it.land_profile_photo,
                            modifier = Modifier.padding(paddings[4][0].dp, paddings[4][1].dp, 0.dp, 0.dp),
                            isLarge = true)
                        landName.value = it.land_name
                        LfState.landName = it.land_name
                    } else {
                        LandSign(name = it.land_name,
                            url = it.land_profile_photo,
                            modifier = Modifier.padding(paddings[farmPageViewModel.index][0].dp, paddings[farmPageViewModel.index][1].dp, 0.dp, 0.dp))

                        // 第5个是个人的放大后padding
                        farmPageViewModel.index = if (farmPageViewModel.index > 7) {
                            0
                        } else if (farmPageViewModel.index < 3 || farmPageViewModel.index > 4) {
                            farmPageViewModel.index + 1
                        } else {
                            5
                        }
                    }
                }
            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = landName.value + "的农场",
                    color = LOHASFarmTheme.colors.headline,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(20.8.dp, 16.64.dp, 0.dp, 12.48.dp)
                        .height(22.88.dp))

                LazyRow(modifier = Modifier.fillMaxWidth().padding(20.8.dp, 0.dp)) {
                    items(items =  plantIntroInfo!!, key = {data -> data.goods_uid}) { data ->
                        PlantCard(
                            data.goods_name,
                            data.plant_root_url,
                            data.plant_state,
                            data.plant_day,
                            data.plant_total_day)
                    }
                }

            }


        }
    }


}


@Composable
fun LandSign(name: String?, url: String, modifier: Modifier = Modifier, isLarge: Boolean = false) {
    Column(modifier = modifier
        .width(
            if (isLarge) {
                62.4.dp
            } else {
                54.08.dp
            }
        )
        .height(
            if (isLarge) {
                91.52.dp
            } else {
                74.88.dp
            }
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .height(
                    if (isLarge) {
                        68.64.dp
                    } else {
                        54.08.dp
                    }
                )
        ) {
            Image(modifier = Modifier
                .padding(0.dp)
                .shadow(
                    elevation = 4.16.dp,
                    shape = RoundedCornerShape(
                        if (isLarge) {
                            31.2.dp
                        } else {
                            24.96.dp
                        }
                    ),
                    clip = true
                )
                .border(2.08.dp, Color.White, CircleShape)
                .width(
                    if (isLarge) {
                        62.4.dp
                    } else {
                        49.92.dp
                    }
                )
                .height(
                    if (isLarge) {
                        62.4.dp
                    } else {
                        49.92.dp
                    }
                ),
                painter = rememberAsyncImagePainter(model = url), contentDescription = null, contentScale = ContentScale.Fit)
            Image(modifier = Modifier
                .shadow(
                    elevation = 0.dp,
                    shape = RoundedCornerShape(0.dp)
                )
                .padding(
                    0.dp, if (isLarge) {
                        61.dp
                    } else {
                        49.3.dp
                    }, 0.dp, 0.dp
                ),
                painter = painterResource(id = R.drawable.ic_land_sign_triangle), contentDescription = null)

        }
        Text(modifier = Modifier
            .width(54.08.dp)
            .height(18.72.dp)
            .shadow(
                elevation = 4.16.dp,
                shape = RoundedCornerShape(4.16.dp),
                clip = true
            )
            .background(LOHASFarmTheme.colors.white),
            text = name + "农场",
            color = LOHASFarmTheme.colors.body2,
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Center)

    }
}


@Composable
fun PlantCard(name: String, url: String, state: String, day: Int, totalDay: Int) {
    Column(modifier = Modifier
        .padding(0.dp, 0.dp, 16.64.dp, 0.dp)
        .shadow(8.32.dp)
        .width(128.96.dp)
        .height(197.6.dp)
        .background(LOHASFarmTheme.colors.white),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(modifier = Modifier
            .padding(12.48.dp)
            .size(104.dp),
            painter = rememberAsyncImagePainter(model = url),
            contentDescription = null,
            contentScale = ContentScale.Fit)
        Row(modifier = Modifier
            .padding(0.dp)
            .width(104.dp)
            .height(20.8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(modifier = Modifier
                .padding(0.dp)
                .width(43.68.dp),
                text = name,
                color = LOHASFarmTheme.colors.body1,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center)

            Text(text = state,
                color = LOHASFarmTheme.colors.color1,
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(0.dp)
                    .width(29.12.dp)
                    .border(0.52.dp, LOHASFarmTheme.colors.color1))
        }

        Box(modifier = Modifier
            .padding(12.48.dp, 8.32.dp)
            .width(104.dp)
            .height(4.16.dp)
            .background(LOHASFarmTheme.colors.background)
            .shadow(0.dp, RoundedCornerShape(2.08.dp)),
            contentAlignment = Alignment.TopStart) {

            Box(modifier = Modifier
                .padding(0.dp)
                .width(if (totalDay > 0) {
                    (104 * day / totalDay).dp
                } else {
                    0.dp
                })
                .height(4.16.dp)
                .background(
                    Brush.linearGradient(
                        listOf(
                            LOHASFarmTheme.colors.color1,
                            Color(0x00CFE567)
                        )
                    )
                )
                .shadow(0.dp, RoundedCornerShape(2.08.dp)))
        }

        Text(text = day.toString() + "/" + totalDay.toString() + "天",
            color = LOHASFarmTheme.colors.body1,
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Start,
            modifier = Modifier.width(104.dp))
    }
}