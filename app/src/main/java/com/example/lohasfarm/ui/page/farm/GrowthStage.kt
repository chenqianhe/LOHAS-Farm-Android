package com.example.lohasfarm.ui.page.farm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lohasfarm.R
import com.example.lohasfarm.logic.network.model.PlantIntroModel
import com.example.lohasfarm.ui.utils.getBarColor
import com.example.lohasfarm.ui.utils.getBarLength
import com.example.lohasfarm.ui.utils.getPlantIcon


@Composable
fun GrowthStage(plantData: List<PlantIntroModel>, modifier: Modifier) {
    Column(modifier = modifier
        .width(348.4.dp)) {
            Row(modifier = Modifier
                .padding(0.dp)
                .height(24.96.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
                Row(modifier = Modifier
                    .padding(0.dp)) {
                    Image(painter = painterResource(id = R.drawable.ic_ya_qi), contentDescription = null)
                    Text(text = "芽期", color = Color(0xFF006B36), style = MaterialTheme.typography.body1)
                }

                Row(modifier = Modifier
                    .padding(0.dp)) {
                    Image(painter = painterResource(id = R.drawable.ic_miao_qi), contentDescription = null)
                    Text(text = "苗期", color = Color(0xFF006B36), style = MaterialTheme.typography.body1)
                }

                Row(modifier = Modifier
                    .padding(0.dp)) {
                    Image(painter = painterResource(id = R.drawable.ic_hua_qi), contentDescription = null)
                    Text(text = "花期", color = Color(0xFF006B36), style = MaterialTheme.typography.body1)
                }

                Row(modifier = Modifier
                    .padding(0.dp)) {
                    Image(painter = painterResource(id = R.drawable.ic_guo_qi), contentDescription = null)
                    Text(text = "果期", color = Color(0xFF006B36), style = MaterialTheme.typography.body1)
                }
            }

        LazyColumn(modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()
            .padding(0.dp, 12.48.dp)) {
            items(plantData, key = {data -> data.goods_uid}) { data ->
                Box(modifier = Modifier
                    .padding(0.dp, 8.32.dp)
                    .fillMaxWidth()
                    .height(45.76.dp),
                contentAlignment = Alignment.CenterStart) {
                    Box(modifier = Modifier
                        .padding(7.28.dp, 0.dp, 0.dp, 0.dp)
                        .height(37.44.dp)
                        .width(getBarLength(data.plant_state))
                        .shadow(7.dp, RoundedCornerShape(18.72.dp), clip = true)
                        .background(getBarColor(data.goods_name))
                    )
                    Image(painter = painterResource(id = getPlantIcon(data.goods_name)),
                        contentDescription = null)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GrowthStagePreview() {
    GrowthStage(listOf(PlantIntroModel("西红柿",
        "123",
        "2",
        "wefwe",
        "萌芽",
        50,
        90),
        PlantIntroModel("西红柿",
            "1234",
            "2",
            "wefwe",
            "苗期",
            50,
            90),
        PlantIntroModel("西红柿",
            "1235",
            "2",
            "wefwe",
            "花期",
            50,
            90),
        PlantIntroModel("西红柿",
            "1236",
            "2",
            "wefwe",
            "果期",
            50,
            90)
    ), Modifier.systemBarsPadding())
}