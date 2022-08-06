package com.example.lohasfarm.logic.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lohasfarm.TOAST_TEST
import com.example.lohasfarm.logic.network.model.UserInfoModel
import com.example.lohasfarm.logic.network.repository.MinePageRepository
import com.example.lohasfarm.logic.utils.LfState
import com.example.lohasfarm.logic.utils.StateCode
import com.example.lohasfarm.ui.utils.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MinePageViewModel(application: Application): AndroidViewModel(application) {
    companion object {
        private const val TAG = "MinePageViewModel"
    }
    private val minePageRepository = MinePageRepository()

    private val _userInfoState = MutableLiveData<UserInfoModel>()
    val userInfoState: LiveData<UserInfoModel>
        get() = _userInfoState

    private val _finished = MutableLiveData<Boolean>(false)
    val finished: LiveData<Boolean>
        get() = _finished


    fun getUserData() {
        viewModelScope.launch {
            val userInfoModel = minePageRepository.getUserData(LfState.uuid)
            Log.i(TAG, userInfoModel.code.toString())
            Log.i(TAG, userInfoModel.msg)

            if (userInfoModel.code == StateCode.GetUserInfoSuccess.code) {
                _userInfoState.value = userInfoModel.content
                _finished.value = true
            }

            if (TOAST_TEST) {
                withContext(Dispatchers.Main) {
                    showToast(
                        getApplication(),
                        userInfoModel.msg
                    )
                }
            }
        }
    }

}