package com.chenqianhe.lohasfarm.ui.page.farm


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.chenqianhe.lohasfarm.R
import com.chenqianhe.lohasfarm.logic.viewModel.OthersLandDetailViewModel
import com.chenqianhe.lohasfarm.ui.main.nav.Actions
import com.chenqianhe.lohasfarm.ui.theme.LOHASFarmTheme

@Composable
fun OthersLandDetail(actions: Actions,
                     landUid: String,
                     name: String,
                     landPlantedArea: Int,
                     landTotalArea: Int,
                     profilePhotoUrl: String) {
    val viewModel: OthersLandDetailViewModel = hiltViewModel()
    viewModel.getPlantData(landUid)
    val isFinished by viewModel.finished.observeAsState(false)
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

        Column(modifier = Modifier.padding(0.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .height(284.96.dp)) {
                Image(modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.others_land_page_background),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth)

                Column(modifier = Modifier
                    .padding(20.8.dp, 186.16.dp, 0.dp, 0.dp)
                    .height(98.8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween) {
                    Image(modifier = Modifier
                        .padding(0.dp)
                        .size(49.92.dp)
                        .shadow(0.dp, RoundedCornerShape(24.96.dp), clip = true)
                        .border(2.08.dp, LOHASFarmTheme.colors.white, RoundedCornerShape(24.96.dp)),
                        painter = rememberAsyncImagePainter(model = profilePhotoUrl.replace("斜杠", "/")),
                        contentDescription = null)

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
            }
        }
    }
}