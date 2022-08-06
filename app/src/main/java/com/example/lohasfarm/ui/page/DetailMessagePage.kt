package com.example.lohasfarm.ui.page


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lohasfarm.logic.viewModel.DetailMessagePageViewModel
import com.example.lohasfarm.ui.main.nav.Actions
import com.example.lohasfarm.ui.theme.LOHASFarmTheme

@Composable
fun DetailMessagePage(actions: Actions, sequenceUid: String, title: String) {
    val viewModel: DetailMessagePageViewModel = hiltViewModel()
    viewModel.getDetailMessageData(sequenceUid)

    val detailMessageState by viewModel.detailMessageInfoState.observeAsState()

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
                            text = title,
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

        if (detailMessageState!!.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                items(items = detailMessageState!!, key = { data -> data.info_uid }) { data ->
                    Detail(
                        date = data.info_date,
                        content = data.info_detail
                    )
                }
            }
        }
    }
}


@Composable
fun Detail(date: String, content: String) {
    Column(modifier = Modifier
        .padding(0.dp, bottom = 20.8.dp)
        .width(348.4.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
            Text(modifier = Modifier.background(Color.Transparent),
                text = date,
                color = LOHASFarmTheme.colors.body2,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(12.48.dp).fillMaxWidth().background(Color.Transparent))

            Text(text = content,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LOHASFarmTheme.colors.white)
                    .padding(12.48.dp),
                color = LOHASFarmTheme.colors.body1,
                style = MaterialTheme.typography.body1)
    }
}

