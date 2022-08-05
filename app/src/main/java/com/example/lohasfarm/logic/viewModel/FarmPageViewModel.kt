package com.example.lohasfarm.logic.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lohasfarm.TOAST_TEST
import com.example.lohasfarm.logic.network.model.LandInfoModel
import com.example.lohasfarm.logic.network.model.PlantIntroModel
import com.example.lohasfarm.logic.network.repository.FarmPageRepository
import com.example.lohasfarm.logic.utils.LfState
import com.example.lohasfarm.logic.utils.StateCode
import com.example.lohasfarm.ui.utils.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 首页ViewModel
 */
class FarmPageViewModel(application: Application): AndroidViewModel(application) {
    companion object {
        private const val TAG = "FarmPageViewModel"
    }

    private val farmPageRepository = FarmPageRepository()

    /**
     * 默认使用本地缓存，再异步更新数据
     */
    private val _landInfoState = MutableLiveData<List<LandInfoModel>>(LfState.landInfo)
    val landInfoState: LiveData<List<LandInfoModel>>
        get() = _landInfoState

    private val _plantIntroInfoState = MutableLiveData<List<PlantIntroModel>>(LfState.plantInfo)
    val plantIntroInfoState: LiveData<List<PlantIntroModel>>
        get() = _plantIntroInfoState

    var index = 0

    fun updateLandInfo() {
        viewModelScope.launch {
            val farmInfoModel = farmPageRepository.getLandData(LfState.uuid)
            Log.i(TAG, farmInfoModel.code.toString())
            Log.i(TAG, farmInfoModel.msg)
            Log.i(TAG, farmInfoModel.content.size.toString())

            if (farmInfoModel.code == StateCode.GetLandInfoSuccess.code) {
                // 刷新index，否则会导致取值越界
                index = 0
                _landInfoState.value = farmInfoModel.content
                // 本地缓存土地数据
                LfState.saveLandInfo(farmInfoModel.content)
            }

            withContext(Dispatchers.Main) {
                showToast(
                    getApplication(),
                    farmInfoModel.msg
                )
            }

        }
    }

    fun updatePlantIntroInfo() {
        viewModelScope.launch {
            val plantInfoModel = farmPageRepository.getPlantIntroData(LfState.uuid)
            Log.i(TAG, plantInfoModel.code.toString())
            Log.i(TAG, plantInfoModel.msg)
            Log.i(TAG, plantInfoModel.content.size.toString())

            if (plantInfoModel.code == StateCode.FetPlantIntroSuccess.code) {
                _plantIntroInfoState.value = plantInfoModel.content
                // 本地缓存土地数据
                LfState.savePlantInfo(plantInfoModel.content)
            }

            withContext(Dispatchers.Main) {
                Log.i(TAG, "!!!!!!")
                showToast(
                    getApplication(),
                    plantInfoModel.msg
                )
            }

        }
    }




}