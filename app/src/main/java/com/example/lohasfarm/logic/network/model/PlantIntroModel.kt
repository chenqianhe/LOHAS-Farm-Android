package com.example.lohasfarm.logic.network.model

data class PlantIntroModel(
    val goods_name: String,
    val goods_uid: String,
    val land_uid: String,
    val plant_root_url: String,
    val plant_state: String,
    val plant_day: Int,
    val plant_total_day: Int
)