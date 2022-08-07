package com.chenqianhe.lohasfarm.logic.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chenqianhe.lohasfarm.TOAST_TEST
import com.chenqianhe.lohasfarm.logic.network.model.DetailMessageModel
import com.chenqianhe.lohasfarm.logic.network.repository.MessagePageRepository
import com.chenqianhe.lohasfarm.logic.utils.LfState
import com.chenqianhe.lohasfarm.logic.utils.StateCode
import com.chenqianhe.lohasfarm.ui.utils.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailMessagePageViewModel(application: Application): AndroidViewModel(application) {
    companion object {
        private const val TAG = "DetailMessageViewModel"
    }
    private val messagePageRepository = MessagePageRepository()
    private val _detailMessageInfoState = MutableLiveData<List<DetailMessageModel>>(listOf())
    val detailMessageInfoState: LiveData<List<DetailMessageModel>>
        get() = _detailMessageInfoState

    fun getDetailMessageData(sequenceUid: String) {
        viewModelScope.launch {
            val detailMessageModel = messagePageRepository.getDetailMessageData(LfState.uuid, sequenceUid)

            Log.i(TAG, detailMessageModel.code.toString())
            Log.i(TAG, detailMessageModel.msg)
            Log.i(TAG, detailMessageModel.content.size.toString())

            if (detailMessageModel.code == StateCode.GetDetailMessageInfoSuccess.code) {
                _detailMessageInfoState.value = detailMessageModel.content
            }

            if (TOAST_TEST) {
                withContext(Dispatchers.Main) {
                    showToast(
                        getApplication(),
                        detailMessageModel.msg
                    )
                }
            }
        }
    }

}