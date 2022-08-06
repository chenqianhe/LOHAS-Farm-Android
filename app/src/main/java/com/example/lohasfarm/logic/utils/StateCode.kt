package com.example.lohasfarm.logic.utils

enum class StateCode(val code: Int) {
    LoginSuccess(100),
    LoginInfoError(101),
    GetLandInfoSuccess(300),
    GetLandInfoError(301),
    GetPlantIntroSuccess(400),
    GetPlantIntroError(401),
    GetFoodActivityInfoSuccess(500),
    GetFoodActivityInfoError(501),
    GetFarmActivityInfoSuccess(600),
    GetFarmActivityInfoError(601),
    GetSequenceInfoSuccess(700),
    GetSequenceInfoError(701),
    GetDetailMessageInfoSuccess(800),
    GetDetailMessageInfoError(801),

}