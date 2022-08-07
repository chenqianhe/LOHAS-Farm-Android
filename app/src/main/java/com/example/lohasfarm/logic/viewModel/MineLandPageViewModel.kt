package com.example.lohasfarm.logic.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lohasfarm.TOAST_TEST
import com.example.lohasfarm.logic.network.model.FullPlantInfoModel
import com.example.lohasfarm.logic.network.model.PlantIntroModel
import com.example.lohasfarm.logic.network.repository.MineFarmPageRepository
import com.example.lohasfarm.logic.utils.LfState
import com.example.lohasfarm.logic.utils.StateCode
import com.example.lohasfarm.ui.utils.showLongToast
import com.example.lohasfarm.ui.utils.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MineLandPageViewModel(application: Application): AndroidViewModel(application) {
    companion object {
        private const val TAG = "MineLandPageViewModel"
    }
    private val mineFarmPageRepository = MineFarmPageRepository()

    private val _plantInfoState = MutableLiveData<List<PlantIntroModel>>()
    val plantInfoState: LiveData<List<PlantIntroModel>>
        get() = _plantInfoState

    private val _finished = MutableLiveData(false)
    val finished: LiveData<Boolean>
        get() = _finished

    private val _addState = MutableLiveData(false)
    val addState: LiveData<Boolean>
        get() = _addState

    private val _plantAddableInfoState = MutableLiveData<List<FullPlantInfoModel>>(listOf())
    val plantAddableInfoState: LiveData<List<FullPlantInfoModel>>
        get() = _plantAddableInfoState

    private val _plantAddedInfoState = MutableLiveData<MutableMap<String, Int>>(mutableMapOf())
    val plantAddedInfoState: LiveData<MutableMap<String, Int>>
        get() = _plantAddedInfoState

    fun getPlantData() {
        viewModelScope.launch {
            val plantInfoModel = mineFarmPageRepository.getPlantIntroData(LfState.uuid)
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

    fun getPlantAddableData() {
        viewModelScope.launch {
            val fullPlantInfoModel = mineFarmPageRepository.getPlantAddableData(LfState.uuid,)
            Log.i(TAG, fullPlantInfoModel.code.toString())
            Log.i(TAG, fullPlantInfoModel.msg)
            Log.i(TAG, fullPlantInfoModel.content.size.toString())

            if (fullPlantInfoModel.code == StateCode.GetPlantAddableSuccess.code) {
                _plantAddableInfoState.value = fullPlantInfoModel.content
                fullPlantInfoModel.content.forEach {
                    _plantAddedInfoState.value!![it.plantInfo_uid] = 0
                }
            }

            if (TOAST_TEST) {
                withContext(Dispatchers.Main) {
                    showToast(
                        getApplication(),
                        fullPlantInfoModel.msg
                    )
                }
            }
        }
    }

    fun changePlantNum(id: String, type: Int) {
        val temp: MutableMap<String, Int> = mutableMapOf()
        temp.putAll(_plantAddedInfoState.value!!)
        if (type > 0) {
            temp[id] = _plantAddedInfoState.value!![id]!!.plus(1)
        } else {
            temp[id] = if (_plantAddedInfoState.value!![id]!! > 0) {
                _plantAddedInfoState.value!![id]!!.minus(1)
            } else {
                0
            }
        }
        _plantAddedInfoState.value = temp
    }

    fun clearAll() {
        val temp: MutableMap<String, Int> = mutableMapOf()
        temp.putAll(_plantAddedInfoState.value!!)

        _plantAddedInfoState.value!!.forEach {
            temp[it.key] = 0
        }
        _plantAddedInfoState.value = temp
    }

    fun uploadAddedPlant() {
        var changeInfo = ""
        _plantAddedInfoState.value!!.forEach {
            if (it.value > 0) {
                changeInfo += "${it.key}=${it.value}/"
            }
        }
        if (changeInfo != "") {
            viewModelScope.launch {
                val fullPlantInfoModel = mineFarmPageRepository.uploadPlantChangeData(LfState.uuid, changeInfo)
                Log.i(TAG, fullPlantInfoModel.code.toString())
                Log.i(TAG, fullPlantInfoModel.msg)
                Log.i(TAG, fullPlantInfoModel.content.size.toString())

                if (fullPlantInfoModel.code == StateCode.GetPlantAddableSuccess.code) {
                    withContext(Dispatchers.Main) {
                        showLongToast(
                            getApplication(),
                            "修改成功上传，核实后会及时同步！"
                        )
                    }
                    _addState.value = false
                }

                if (TOAST_TEST) {
                    withContext(Dispatchers.Main) {
                        showToast(
                            getApplication(),
                            fullPlantInfoModel.msg
                        )
                    }
                }
            }
        }
    }

    fun disableAdd() {
        _addState.value = false
    }

    fun enableAdd() {
        _addState.value = true
    }

}