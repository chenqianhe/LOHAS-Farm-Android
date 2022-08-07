package com.example.lohasfarm.ui.page.farm


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.lohasfarm.R
import com.example.lohasfarm.logic.viewModel.PlantingTutorialViewModel
import com.example.lohasfarm.ui.main.nav.Actions
import com.example.lohasfarm.ui.theme.LOHASFarmTheme


@Composable
fun PlantingTutorialPage(actions: Actions) {
    val viewModel: PlantingTutorialViewModel = hiltViewModel()
    viewModel.getTutorInfo()
    val tutorState by viewModel.tutorDataState.observeAsState()

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
                            text = "种植教程",
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
        
        LazyVerticalGrid(modifier = Modifier.padding(20.8.dp, 18.72.dp), columns = GridCells.Fixed(2)) {
            items(tutorState!!) { data ->
                Item(
                    title = data.tutor_title,
                    date = data.tutor_date,
                    photoUrl = data.tutor_photo_url,
                    view = data.tutor_view,
                    like = data.tutor_like,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        actions.toWebPage(data.tutor_url.replace("/", "斜杠"), data.tutor_title)
                    }
                )
            }
        }

    }
}


@Composable
fun Item(title: String, date: String, photoUrl: String, view: Int, like: Int, modifier: Modifier) {
    Column(modifier = modifier
        .padding(0.dp, 8.32.dp)
        .width(166.4.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceAround) {
            Image(modifier = Modifier
                .padding(0.dp)
                .size(166.4.dp, 124.8.dp)
                .shadow(0.dp, RoundedCornerShape(8.32.dp), clip = true),
                painter = rememberAsyncImagePainter(model = photoUrl),
                contentDescription = null,
                contentScale = ContentScale.FillWidth)

            Text(modifier = Modifier
                .padding(0.dp)
                .width(166.4.dp),
                text = title,
                color = LOHASFarmTheme.colors.headline,
                style = MaterialTheme.typography.body1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)

            Text(text = date,
                color = LOHASFarmTheme.colors.footnote,
                style = MaterialTheme.typography.body2)

            Row(modifier = Modifier.padding(0.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom) {
                Image(modifier = Modifier
                    .padding(0.dp)
                    .size(16.67.dp),
                    painter = painterResource(id = R.drawable.ic_like),
                    contentDescription = null,
                    contentScale = ContentScale.Fit)

                Text(text = like.toString(),
                    color = LOHASFarmTheme.colors.footnote,
                    style = MaterialTheme.typography.body2)

                Spacer(modifier = Modifier.padding(0.dp).width(10.dp))

                Image(modifier = Modifier
                    .padding(0.dp)
                    .size(16.67.dp),
                    painter = painterResource(id = R.drawable.ic_view),
                    contentDescription = null,
                    contentScale = ContentScale.Fit)

                Text(text = view.toString(),
                    color = LOHASFarmTheme.colors.footnote,
                    style = MaterialTheme.typography.body2)
            }
    }
}