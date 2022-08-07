package com.chenqianhe.lohasfarm.logic.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chenqianhe.lohasfarm.TOAST_TEST
import com.chenqianhe.lohasfarm.logic.network.model.PlantIntroModel
import com.chenqianhe.lohasfarm.logic.network.repository.FarmPageRepository
import com.chenqianhe.lohasfarm.logic.utils.LfState
import com.chenqianhe.lohasfarm.logic.utils.StateCode
import com.chenqianhe.lohasfarm.ui.utils.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OthersLandDetailViewModel(application: Application): AndroidViewModel(application) {
    companion object {
        private const val TAG = "OthersLandViewModel"
    }
    private val farmPageRepository = FarmPageRepository()

    private val _plantInfoState = MutableLiveData<List<PlantIntroModel>>()
    val plantInfoState: LiveData<List<PlantIntroModel>>
        get() = _plantInfoState

    private val _finished = MutableLiveData(false)
    val finished: LiveData<Boolean>
        get() = _finished

    fun getPlantData(landUid: String) {
        viewModelScope.launch {
            val plantInfoModel = farmPageRepository.getOthersPlantData(LfState.uuid, landUid)
            Log.i(TAG, plantInfoModel.code.toString())
            Log.i(TAG, plantInfoModel.msg)
            Log.i(TAG, plantInfoModel.content.size.toString())

            if (plantInfoModel.code == StateCode.GetPlantIntroSuccess.code) {
                _plantInfoState.value = plantInfoModel.content
                _finished.value = true
            }

            if (TOAST_TEST) {
                withContext(Dispatchers.Main) {
                    showToast(
                        getApplication(),
                        plantInfoModel.msg
                    )
                }
            }
        }
    }


}