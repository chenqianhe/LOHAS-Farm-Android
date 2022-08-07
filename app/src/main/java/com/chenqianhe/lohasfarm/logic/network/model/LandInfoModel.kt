package com.chenqianhe.lohasfarm.logic.network.model

data class LandInfoModel(
    val is_mine: Boolean,
    val land_lease_term: String,
    val land_name: String,
    val land_planted_area: Int,
    val land_profile_photo: String,
    val land_soil_type: String,
    val land_total_area: Int,
    val land_uid: String
)
