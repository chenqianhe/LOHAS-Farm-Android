package com.example.lohasfarm.logic.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lohasfarm.logic.network.model.LandInfoModel
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
    private val _landInfoState = MutableLiveData<List<LandInfoModel>>(LfState.landInfo)
    private val farmPageRepository = FarmPageRepository()

    val landInfoState: LiveData<List<LandInfoModel>>
        get() = _landInfoState

    fun updateLandInfo() {
        viewModelScope.launch {
            val farmInfoModel = farmPageRepository.getLandData(LfState.uuid)
            Log.i(TAG, farmInfoModel.code.toString())
            Log.i(TAG, farmInfoModel.msg)

            if (farmInfoModel.code == StateCode.GetLandInfoSuccess.code) {
                _landInfoState.value = farmInfoModel.content
            }

            withContext(Dispatchers.Main) {
                showToast(
                    getApplication(),
                    farmInfoModel.msg
                )
            }
        }
    }


}