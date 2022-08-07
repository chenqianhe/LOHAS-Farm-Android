package com.chenqianhe.lohasfarm.logic.viewModel


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chenqianhe.lohasfarm.TOAST_TEST
import com.chenqianhe.lohasfarm.logic.network.model.TutorModel
import com.chenqianhe.lohasfarm.logic.network.repository.PlantingTutorialRepository
import com.chenqianhe.lohasfarm.logic.utils.LfState
import com.chenqianhe.lohasfarm.logic.utils.StateCode
import com.chenqianhe.lohasfarm.ui.utils.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlantingTutorialViewModel(application: Application): AndroidViewModel(application) {
    companion object {
        private const val TAG = "TutorialViewModel"
    }
    private val plantingTutorialRepository = PlantingTutorialRepository()

    private val _tutorDataState = MutableLiveData<List<TutorModel>>(listOf())
    val tutorDataState: LiveData<List<TutorModel>>
        get() = _tutorDataState

    fun getTutorInfo() {
        viewModelScope.launch {
            val tutorInfoModel = plantingTutorialRepository.getTutorData(LfState.uuid)
            Log.i(TAG, tutorInfoModel.code.toString())
            Log.i(TAG, tutorInfoModel.msg)
            Log.i(TAG, tutorInfoModel.content.size.toString())

            if (tutorInfoModel.code == StateCode.GetTutorSuccess.code) {
                _tutorDataState.value = tutorInfoModel.content
            }

            if (TOAST_TEST) {
                withContext(Dispatchers.Main) {
                    showToast(
                        getApplication(),
                        tutorInfoModel.msg
                    )
                }
            }
        }
    }

}