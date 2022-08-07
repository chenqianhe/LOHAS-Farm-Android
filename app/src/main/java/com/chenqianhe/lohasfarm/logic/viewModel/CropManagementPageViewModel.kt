package com.chenqianhe.lohasfarm.logic.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chenqianhe.lohasfarm.TOAST_TEST
import com.chenqianhe.lohasfarm.logic.network.model.OpModel
import com.chenqianhe.lohasfarm.logic.network.model.PlantIntroModel
import com.chenqianhe.lohasfarm.logic.network.repository.CropManagementPageRepository
import com.chenqianhe.lohasfarm.logic.utils.LfState
import com.chenqianhe.lohasfarm.logic.utils.StateCode
import com.chenqianhe.lohasfarm.ui.utils.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CropManagementPageViewModel(application: Application): AndroidViewModel(application) {
    companion object {
        private const val TAG = "MineLandPageViewModel"
    }

    private val cropManagementPageRepository = CropManagementPageRepository()

    private val _plantInfoState = MutableLiveData<List<PlantIntroModel>>(listOf())
    val plantInfoState: LiveData<List<PlantIntroModel>>
        get() = _plantInfoState

    private val _opState = MutableLiveData<List<OpModel>>(listOf())
    val opState: LiveData<List<OpModel>>
        get() = _opState

    fun getPlantData() {
        viewModelScope.launch {
            val plantInfoModel = cropManagementPageRepository.getPlantIntroData(LfState.uuid)
            Log.i(TAG, plantInfoModel.code.toString())
            Log.i(TAG, plantInfoModel.msg)
            Log.i(TAG, plantInfoModel.content.size.toString())

            if (plantInfoModel.code == StateCode.GetPlantIntroSuccess.code) {
                _plantInfoState.value = plantInfoModel.content
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

    fun getOpData() {
        viewModelScope.launch {
            val opModel = cropManagementPageRepository.getOperationData(LfState.uuid)
            Log.i(TAG, opModel.code.toString())
            Log.i(TAG, opModel.msg)
            Log.i(TAG, opModel.content.size.toString())

            if (opModel.code == StateCode.GetOpInfoSuccess.code) {
                _opState.value = opModel.content
            }

            if (TOAST_TEST) {
                withContext(Dispatchers.Main) {
                    showToast(
                        getApplication(),
                        opModel.msg
                    )
                }
            }
        }
    }


}