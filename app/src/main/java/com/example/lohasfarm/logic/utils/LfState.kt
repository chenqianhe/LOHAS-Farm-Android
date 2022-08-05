package com.example.lohasfarm.logic.utils

import com.example.lohasfarm.logic.database.*
import com.example.lohasfarm.logic.network.model.BaseModel
import com.example.lohasfarm.logic.network.model.LandInfoModel
import com.example.lohasfarm.logic.network.model.LoginModel
import com.example.lohasfarm.logic.network.model.PlantIntroModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Scope


/**
 * 全局数据接口
 */
object LfState {
    private const val USER_UUID = "uuid"
    private const val USER_UGID = "ugid"
    private const val USER_UID = "uid"
    private const val USER_ADMIN = "admin"
    private const val IS_LOGIN = "isLogin"
    private const val LAND_NAME = "landName"
    private lateinit var dataStore: DataStoreUtils
    private lateinit var landInfoModelDao: FarmInfoDao
    private lateinit var plantIntroInfoModelDao: PlantIntroInfoDao
    var landInfo: MutableList<LandInfoModel> = mutableListOf()
    var plantInfo: MutableList<PlantIntroModel> = mutableListOf()

    /**
     * 初始化
     */
    fun initialize(dataStoreUtils: DataStoreUtils, farmInfoDb: FarmInfoDao, plantIntroInfoDb: PlantIntroInfoDao) {
        dataStore = dataStoreUtils
        landInfoModelDao = farmInfoDb
        plantIntroInfoModelDao = plantIntroInfoDb
        val farmInfo = landInfoModelDao.queryAll()
        val plantIntroInfo = plantIntroInfoModelDao.queryAll()
        // 加载本地数据
        if (this.isLogin) {
            farmInfo.forEach {
                landInfo.add(
                    LandInfoModel(
                    land_uid = it.landUid,
                    land_name = it.landName,
                    land_profile_photo = it.landProfilePhoto,
                    is_mine = it.isMine)
                )
            }

            plantIntroInfo.forEach {
                plantInfo.add(
                    PlantIntroModel(
                        land_uid = it.land_uid,
                        goods_uid = it.goods_uid,
                        goods_name = it.goods_name,
                        plant_state = it.plant_state,
                        plant_root_url = it.plant_root_url,
                        plant_day = it.plant_day,
                        plant_total_day = it.plant_total_day
                    )
                )
            }

        }
    }

    /**
     * 清除所有信息
     */
    @OptIn(DelicateCoroutinesApi::class)
    fun clearAll()= GlobalScope.launch{
        dataStore.clear()
    }

    /**
     * 登陆状态
     */
    var isLogin: Boolean
        get() = dataStore.getDataSync(IS_LOGIN, false)
        set(value) {
            dataStore.putDataSync(IS_LOGIN, value)
        }

    /**
     * UUID
     */
    var uuid: String
        get() = dataStore.getDataSync(USER_UUID, "0")
        set(value) {
            dataStore.putDataSync(USER_UUID, value)
        }

    /**
     * UID
     */
    var uid: String
        get() = dataStore.getDataSync(USER_UID, "0")
        set(value) {
            dataStore.putDataSync(USER_UID, value)
        }

    /**
     * UGID
     */
    var ugid: String
        get() = dataStore.getDataSync(USER_UGID, "0")
        set(value) {
            dataStore.putDataSync(USER_UGID, value)
        }

    /**
     * UUID
     */
    var admin: Int
        get() = dataStore.getDataSync(USER_ADMIN, 0)
        set(value) {
            dataStore.putDataSync(USER_ADMIN, value)
        }

    /**
     * 土地名称
     */
    var landName: String
        get() = dataStore.getDataSync(LAND_NAME, "我")
        set(value) {
            dataStore.putDataSync(LAND_NAME, value)
        }

    /**
     * 存储用户基本信息
     */
    fun saveLoginState(loginModel: BaseModel<LoginModel>) {
        if (loginModel.code == StateCode.LoginSuccess.code) {
            val loginContent = loginModel.content

            this.uuid = loginContent.uuid
            this.uid = loginContent.uid
            this.ugid = loginContent.ugid
            this.admin = loginContent.admin
        }
    }

    /**
     * 存储土地信息
     */
    @OptIn(DelicateCoroutinesApi::class)
    fun saveLandInfo(landInfo: List<LandInfoModel>) {
        GlobalScope.launch(Dispatchers.IO){
            landInfoModelDao.deleteAll()
            val farmInfo = FarmInfo()
            landInfo.forEach {
                farmInfo.landUid = it.land_uid
                farmInfo.landName = it.land_name
                farmInfo.landProfilePhoto = it.land_profile_photo
                farmInfo.isMine = it.is_mine
                landInfoModelDao.insert(farmInfo)
            }
        }
    }

    /**
     * 存储作物信息
     */
    @OptIn(DelicateCoroutinesApi::class)
    fun savePlantInfo(plantInfo: List<PlantIntroModel>) {
        GlobalScope.launch(Dispatchers.IO){
            plantIntroInfoModelDao.deleteAll()
            val plantIntroInfo = PlantIntroInfo()
            plantInfo.forEach {
                plantIntroInfo.land_uid = it.land_uid
                plantIntroInfo.goods_uid = it.goods_uid
                plantIntroInfo.goods_name = it.goods_name
                plantIntroInfo.plant_root_url = it.plant_root_url
                plantIntroInfo.plant_state = it.plant_state
                plantIntroInfo.plant_day = it.plant_day
                plantIntroInfo.plant_total_day = it.plant_total_day
                plantIntroInfoModelDao.insert(plantIntroInfo)
            }
        }
    }

}