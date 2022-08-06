package com.example.lohasfarm.logic.utils

enum class StateCode(val code: Int) {
    LoginSuccess(100),
    LoginInfoError(101),
    GetUserInfoSuccess(200),
    GetUserInfoError(201),
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
    GetPlantAddableSuccess(900),
    GetPlantAddableError(901),
}