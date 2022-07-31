package com.example.lohasfarm.logic.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lohasfarm.logic.network.model.BaseModel
import com.example.lohasfarm.logic.network.model.LoginModel
import com.example.lohasfarm.logic.network.repository.AccountRepository
import com.example.lohasfarm.logic.utils.LfState
import com.example.lohasfarm.logic.utils.StateCode
import com.example.lohasfarm.ui.utils.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginPageViewModel (application: Application) : AndroidViewModel(application) {

    companion object {
        private const val TAG = "LoginViewModel"
    }

    private val _state = MutableLiveData(false)

    private val accountRepository = AccountRepository()

    fun login(account: String, password: String) = viewModelScope.async(Dispatchers.IO) {
        val loginModel: BaseModel<LoginModel> = accountRepository.login(account, password)
        Log.i(TAG, loginModel.msg)
        Log.i(TAG, loginModel.content.uuid)

        if (loginModel.code == StateCode.LoginSuccess.code) {
            LfState.isLogin = true
            _state.postValue(true)
            LfState.saveLoginState(loginModel)
        } else {
            LfState.isLogin = false
            _state.postValue(false)
        }

        withContext(Dispatchers.Main) {
            showToast(
                getApplication(),
                loginModel.msg
            )
        }

    }
}