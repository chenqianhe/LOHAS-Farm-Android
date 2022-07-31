package com.example.lohasfarm.ui.page.farm

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.lohasfarm.R
import com.example.lohasfarm.logic.utils.LfState
import com.example.lohasfarm.ui.main.nav.Actions
import com.example.lohasfarm.ui.theme.LOHASFarmTheme


private const val TAG = "FARM_PAGE"

@Composable
fun FarmPage(actions: Actions) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_farm_page_background),
                    contentDescription = "farm_page_background",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillBounds)
                Text(
                    modifier = Modifier.systemBarsPadding(),
                    text = "Hello FarmPage!",
                    color = LOHASFarmTheme.colors.color1,
                    style = MaterialTheme.typography.h1)
        }

        Button(onClick = {
            LfState.clearAll()
            actions.toLoginPage()
        }) {
            Text(text = "Log out")
        }

    }
}
