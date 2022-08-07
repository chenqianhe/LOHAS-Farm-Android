package com.chenqianhe.lohasfarm.ui.page.farm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.chenqianhe.lohasfarm.R
import com.chenqianhe.lohasfarm.logic.viewModel.CropManagementPageViewModel
import com.chenqianhe.lohasfarm.ui.main.nav.Actions
import com.chenqianhe.lohasfarm.ui.theme.LOHASFarmTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun CropManagementPage(actions: Actions) {
    val viewModel: CropManagementPageViewModel = hiltViewModel()
    viewModel.getPlantData()
    viewModel.getOpData()
    
    val plantData by viewModel.plantInfoState.observeAsState()
    val opData by viewModel.opState.observeAsState()
    val state = rememberPagerState(initialPage = 0)

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
                            text = "作物管理",
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
        LazyColumn(modifier = Modifier.padding(20.8.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
            item {
                Box(modifier = Modifier
                    .padding(0.dp)
                    .width(348.4.dp)
                    .height(478.4.dp)) {
                    HorizontalPager(count = plantData!!.size, state = state) { pager ->
                        Image(modifier = Modifier
                            .padding(0.dp)
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .shadow(0.dp, RoundedCornerShape(16.64.dp), clip = true),
                            painter = rememberAsyncImagePainter(model = plantData!![pager].plant_root_url),
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight)
                    }
                    if (plantData!!.isNotEmpty()) {
                        Column(modifier = Modifier
                            .padding(26.68.dp, 400.dp, 26.68.dp, 0.dp)
                            .width(295.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center) {
                            Row(modifier = Modifier
                                .padding(0.dp)
                                .height(22.88.dp)
                            ) {
                                Text(modifier = Modifier
                                    .padding(4.16.dp, 0.dp)
                                    .fillMaxHeight(),
                                    text = plantData!![state.currentPage].goods_name,
                                    color = LOHASFarmTheme.colors.white,
                                    style = MaterialTheme.typography.h5,
                                    textAlign = TextAlign.Center)

                                Text(modifier = Modifier
                                    .padding(4.16.dp, 0.dp)
                                    .width(49.92.dp)
                                    .height(21.84.dp)
                                    .shadow(0.dp, RoundedCornerShape(2.08.dp), clip = true)
                                    .background(Color(0x40FFFFFF)),
                                    text = plantData!![state.currentPage].plant_state,
                                    color = LOHASFarmTheme.colors.white,
                                    style = MaterialTheme.typography.body2,
                                    textAlign = TextAlign.Center)
                            }

                            Spacer(modifier = Modifier
                                .padding(0.dp)
                                .height(4.16.dp))

                            Row(modifier = Modifier
                                .padding(0.dp)
                                .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Box(modifier = Modifier
                                    .padding(0.dp)
                                    .width(218.4.dp)
                                    .height(6.24.dp)
                                    .shadow(0.dp, RoundedCornerShape(3.12.dp), clip = true)
                                    .background(LOHASFarmTheme.colors.background),
                                    contentAlignment = Alignment.TopStart) {

                                    Box(modifier = Modifier
                                        .padding(0.dp)
                                        .width(
                                            if (plantData!![state.currentPage].plant_total_day > 0) {
                                                (218.4 * plantData!![state.currentPage].plant_day / plantData!![state.currentPage].plant_total_day).dp
                                            } else {
                                                0.dp
                                            }
                                        )
                                        .height(6.24.dp)
                                        .shadow(0.dp, RoundedCornerShape(3.12.dp), clip = true)
                                        .background(
                                            Brush.linearGradient(
                                                listOf(
                                                    LOHASFarmTheme.colors.color1,
                                                    Color(0x80CFE567)
                                                )
                                            )
                                        ))
                                }

                                Text(text = "${plantData!![state.currentPage].plant_day}/${plantData!![state.currentPage].plant_total_day}天",
                                    color = LOHASFarmTheme.colors.white,
                                    style = MaterialTheme.typography.body2)
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(0.dp, 40.dp, 0.dp, 0.dp)
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(modifier = Modifier
                        .padding(0.dp)
                        .size(50.dp),
                        painter = painterResource(id = R.drawable.ic_water),
                        contentDescription = null)

                    Image(modifier = Modifier
                        .padding(0.dp)
                        .size(50.dp),
                        painter = painterResource(id = R.drawable.ic_fertilizer),
                        contentDescription = null)

                    Image(modifier = Modifier
                        .padding(0.dp)
                        .size(50.dp),
                        painter = painterResource(id = R.drawable.ic_weed),
                        contentDescription = null)

                    Image(modifier = Modifier
                        .padding(0.dp)
                        .size(50.dp),
                        painter = painterResource(id = R.drawable.trim),
                        contentDescription = null)

                    Image(modifier = Modifier
                        .padding(0.dp)
                        .size(50.dp),
                        painter = painterResource(id = R.drawable.ic_harvest_button),
                        contentDescription = null)
                }

                LazyRow(modifier = Modifier
                    .padding(0.dp, 22.88.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                    items(items =  opData!!.filter { it.op_plant_uid == plantData!![state.currentPage].goods_uid },
                        key = {data -> data.date}) { data ->
                        Column(modifier = Modifier
                            .padding(0.dp, 12.48.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = data.week_day,
                                color = LOHASFarmTheme.colors.headline,
                                style = MaterialTheme.typography.caption,
                                textAlign = TextAlign.Center)

                            Box(modifier = Modifier
                                .padding(0.dp, 8.32.dp)
                                .size(31.2.dp),
                            contentAlignment = Alignment.Center) {
                                Text(text = data.date.substring(5).replace("-", "."),
                                    color = LOHASFarmTheme.colors.body2,
                                    style = MaterialTheme.typography.caption)

                                if (data.op_state > 0) {
                                    Image(modifier = Modifier.padding(0.dp)
                                        .size(31.2.dp),
                                        painter = painterResource(id = when (data.op_state) {
                                            1 -> R.drawable.ic_1
                                            2 -> R.drawable.ic_2
                                            3 -> R.drawable.ic_3
                                            4 -> R.drawable.ic_4
                                            else -> R.drawable.ic_1
                                        }),
                                        contentDescription = null
                                    )
                                }
                            }

                            if (data.op_special_tag > 0) {
                                Image(modifier = Modifier.padding(0.dp)
                                    .size(31.2.dp),
                                    painter = painterResource(id = when (data.op_state) {
                                        1 -> R.drawable.ic_farming
                                        2 -> R.drawable.ic_sprouting
                                        3 -> R.drawable.ic_seeding
                                        4 -> R.drawable.ic_bloom
                                        5 -> R.drawable.ic_harvest
                                        else -> R.drawable.ic_farming
                                    }),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}