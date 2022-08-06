package com.example.lohasfarm.logic.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lohasfarm.TOAST_TEST
import com.example.lohasfarm.logic.network.model.SequenceInfoModel
import com.example.lohasfarm.logic.network.repository.MessagePageRepository
import com.example.lohasfarm.logic.utils.LfState
import com.example.lohasfarm.logic.utils.StateCode
import com.example.lohasfarm.ui.utils.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MessagePageViewModel(application: Application): AndroidViewModel(application) {
    companion object {
        private const val TAG = "MessagePageViewModel"
    }
    private val messagePageRepository = MessagePageRepository()
    /**
     * true代表农场通知界面， false代表系统公告界面
     */
    private val _messageBoxState = MutableLiveData(true)
    val messageBoxState: LiveData<Boolean> = _messageBoxState

    private val _sequenceInfoState = MutableLiveData<List<SequenceInfoModel>>(listOf())
    val sequenceInfoState: LiveData<List<SequenceInfoModel>>
        get() = _sequenceInfoState

    fun changeMessageBoxState() {
        _messageBoxState.value = !_messageBoxState.value!!
    }

    fun getSequenceInfoData() {
        viewModelScope.launch {
            val sequenceInfoModel = messagePageRepository.getSequenceData(LfState.uuid)
            Log.i(TAG, sequenceInfoModel.code.toString())
            Log.i(TAG, sequenceInfoModel.msg)
            Log.i(TAG, sequenceInfoModel.content.size.toString())

            if (sequenceInfoModel.code == StateCode.GetSequenceInfoSuccess.code) {
                _sequenceInfoState.value = sequenceInfoModel.content
            }

            if (TOAST_TEST) {
                withContext(Dispatchers.Main) {
                    showToast(
                        getApplication(),
                        sequenceInfoModel.msg
                    )
                }
            }
        }
    }

}