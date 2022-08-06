package com.example.lohasfarm.logic.network.model

data class FullPlantInfoModel(
    val plantInfo_add_enable: Int,
    val plantInfo_name: String,
    val plantInfo_photo_url: String,
    val plantInfo_total_day: Int,
    val plantInfo_type: String,
    val plantInfo_uid: String
)