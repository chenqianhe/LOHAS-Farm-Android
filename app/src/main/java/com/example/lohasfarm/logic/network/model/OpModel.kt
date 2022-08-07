package com.example.lohasfarm.logic.network.model

data class OpModel(
    val date: String,
    val op_plant_uid: String,
    val op_special_tag: Int,
    val op_state: Int,
    val week_day: String
)