package com.chenqianhe.lohasfarm.logic.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chenqianhe.lohasfarm.TOAST_TEST
import com.chenqianhe.lohasfarm.logic.network.model.ActivityModel
import com.chenqianhe.lohasfarm.logic.network.repository.ActivityPageRepository
import com.chenqianhe.lohasfarm.logic.utils.LfState
import com.chenqianhe.lohasfarm.logic.utils.StateCode
import com.chenqianhe.lohasfarm.ui.utils.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityPageViewModel(application: Application): AndroidViewModel(application) {
    companion object {
        private const val TAG = "ActivityPageViewModel"
    }
    private val activityPageRepository = ActivityPageRepository()
    /**
     * true代表农副产品界面， false代表农场活动界面
     */
    private val _activityItemState = MutableLiveData(true)
    val activityItemState: LiveData<Boolean> = _activityItemState

    private val _foodActivityInfoState = MutableLiveData<List<ActivityModel>>(listOf())
    val foodActivityInfoState: LiveData<List<ActivityModel>>
        get() = _foodActivityInfoState

    private val _farmActivityInfoState = MutableLiveData<List<ActivityModel>>(listOf())
    val farmActivityInfoState: LiveData<List<ActivityModel>>
        get() = _farmActivityInfoState

    fun changeActivityItemState() {
        _activityItemState.value = !_activityItemState.value!!
    }

    fun getFoodActivityInfo() {
        viewModelScope.launch {
            val foodActivityInfoModel = activityPageRepository.getFoodActivityData(LfState.uuid)
            Log.i(TAG, foodActivityInfoModel.code.toString())
            Log.i(TAG, foodActivityInfoModel.msg)
            Log.i(TAG, foodActivityInfoModel.content.size.toString())

            if (foodActivityInfoModel.code == StateCode.GetFoodActivityInfoSuccess.code) {
                _foodActivityInfoState.value = foodActivityInfoModel.content
            }

            if (TOAST_TEST) {
                withContext(Dispatchers.Main) {
                    showToast(
                        getApplication(),
                        foodActivityInfoModel.msg
                    )
                }
            }
        }
    }

    fun getFarmActivityInfo() {
        viewModelScope.launch {
            val farmActivityInfoModel = activityPageRepository.getFarmActivityData(LfState.uuid)
            Log.i(TAG, farmActivityInfoModel.code.toString())
            Log.i(TAG, farmActivityInfoModel.msg)
            Log.i(TAG, farmActivityInfoModel.content.size.toString())

            if (farmActivityInfoModel.code == StateCode.GetFarmActivityInfoSuccess.code) {
                _farmActivityInfoState.value = farmActivityInfoModel.content
            }

            if (TOAST_TEST) {
                withContext(Dispatchers.Main) {
                    showToast(
                        getApplication(),
                        farmActivityInfoModel.msg
                    )
                }
            }
        }
    }

}