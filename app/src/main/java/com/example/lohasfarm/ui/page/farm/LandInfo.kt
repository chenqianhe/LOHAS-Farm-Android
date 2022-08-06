package com.example.lohasfarm.ui.page.farm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.lohasfarm.R
import com.example.lohasfarm.ui.main.nav.Actions
import com.example.lohasfarm.ui.theme.LOHASFarmTheme


@Composable
fun LandInfoPage(actions: Actions, name: String, landLeaseTerm: String, area: String) {
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
                            text = "地块信息",
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
                .fillMaxWidth()) {
                Image(modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.others_land_page_background),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth)
            }

            Row(modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .height(49.92.dp)
                .padding(20.8.dp, 14.56.dp)) {
                Text(text = "农场昵称",
                    color = LOHASFarmTheme.colors.body2,
                    style = MaterialTheme.typography.body1)

                Text(modifier = Modifier.padding(8.32.dp, 0.dp),
                    text = "${name}农场",
                    color = LOHASFarmTheme.colors.headline,
                    style = MaterialTheme.typography.body1)
            }

            Row(modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .height(49.92.dp)
                .padding(20.8.dp, 14.56.dp)) {
                Text(text = "地块租期",
                    color = LOHASFarmTheme.colors.body2,
                    style = MaterialTheme.typography.body1)

                Text(modifier = Modifier.padding(8.32.dp, 0.dp),
                    text = landLeaseTerm,
                    color = LOHASFarmTheme.colors.headline,
                    style = MaterialTheme.typography.body1)
            }

            Row(modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .height(49.92.dp)
                .padding(20.8.dp, 14.56.dp)) {
                Text(text = "地块面积",
                    color = LOHASFarmTheme.colors.body2,
                    style = MaterialTheme.typography.body1)

                Text(modifier = Modifier.padding(8.32.dp, 0.dp),
                    text = area.replace("\\", "/"),
                    color = LOHASFarmTheme.colors.headline,
                    style = MaterialTheme.typography.body1)
            }
        }
    }
}