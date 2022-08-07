package com.example.lohasfarm.ui.page.mine

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.lohasfarm.ui.main.nav.Actions
import com.example.lohasfarm.ui.theme.LOHASFarmTheme

@Composable
fun PersonalInfoPage(actions: Actions,
                     uid: String,
                     ugid: String,
                     name: String,
                     profile_photo_url: String) {
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
                        .padding(75.dp, 0.dp)
                        .fillMaxHeight()
                        .fillMaxWidth(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center) {
                        Text(modifier = Modifier
                            .padding(0.dp)
                            .width(104.dp),
                            text = "个人信息",
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
        },
        backgroundColor = LOHASFarmTheme.colors.background) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
            Column(modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .background(LOHASFarmTheme.colors.white)) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(49.92.dp)
                    .background(LOHASFarmTheme.colors.white)
                    .padding(20.8.dp, 14.56.dp, 0.dp, 14.56.dp),
                ) {
                    Text(text = "用户ID", color = LOHASFarmTheme.colors.body2, style = MaterialTheme.typography.body1)
                    Text(text = uid, color = LOHASFarmTheme.colors.headline, style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(65.dp, 0.dp, 0.dp, 0.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start)
                }

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(49.92.dp)
                    .background(LOHASFarmTheme.colors.white)
                    .padding(20.8.dp, 14.56.dp, 0.dp, 14.56.dp),
                ) {
                    Text(text = "家庭ID", color = LOHASFarmTheme.colors.body2, style = MaterialTheme.typography.body1)
                    Text(text = ugid, color = LOHASFarmTheme.colors.headline, style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(65.dp, 0.dp, 0.dp, 0.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start)
                }

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(49.92.dp)
                    .background(LOHASFarmTheme.colors.white)
                    .padding(20.8.dp, 14.56.dp, 0.dp, 14.56.dp),
                ) {
                    Text(text = "我的昵称", color = LOHASFarmTheme.colors.body2, style = MaterialTheme.typography.body1)
                    Text(text = name, color = LOHASFarmTheme.colors.headline, style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(65.dp, 0.dp, 0.dp, 0.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start)
                }

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(49.92.dp)
                    .background(LOHASFarmTheme.colors.white)
                    .padding(20.8.dp, 0.dp, 0.dp, 0.dp),
                ) {
                    Text(text = "用户ID", color = LOHASFarmTheme.colors.body2, style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(0.dp, 14.56.dp, 0.dp, 14.56.dp))
                    Image(modifier = Modifier
                        .padding(65.dp, 8.32.dp, 0.dp, 0.dp)
                        .size(33.28.dp)
                        .shadow(elevation = 0.dp,
                            shape = RoundedCornerShape(16.64.dp),
                            clip = true
                        ),
                        painter = rememberAsyncImagePainter(model = profile_photo_url.replace("斜杠", "/")),
                        contentDescription = null,
                        contentScale = ContentScale.Fit)
                }

            }
    }
}