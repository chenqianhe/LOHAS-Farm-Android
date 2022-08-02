package com.example.lohasfarm.logic.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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
import kotlinx.coroutines.withContext


class LoginPageViewModel (application: Application) : AndroidViewModel(application) {

    companion object {
        private const val TAG = "LoginViewModel"
    }

    /**
     * true代表登陆界面， false代表注册界面
     */
    private val _loginState = MutableLiveData(true)
    val loginState: LiveData<Boolean> = _loginState

    fun changeLoginState() {
        _loginState.value = !_loginState.value!!
    }

    private val _account = MutableLiveData("")
    val account: LiveData<String> = _account

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _password2 = MutableLiveData("")
    val password2: LiveData<String> = _password2

    private val accountRepository = AccountRepository()

    fun login(account: String, password: String) = viewModelScope.async(Dispatchers.IO) {
        val loginModel: BaseModel<LoginModel> = accountRepository.login(account, password)
        Log.i(TAG, loginModel.msg)
        Log.i(TAG, loginModel.content.uuid)

        if (loginModel.code == StateCode.LoginSuccess.code) {
            LfState.isLogin = true
            LfState.saveLoginState(loginModel)
        } else {
            LfState.isLogin = false
        }

        withContext(Dispatchers.Main) {
            showToast(
                getApplication(),
                loginModel.msg
            )
        }

    }

    fun updateAccount(it: String) {
        _account.value = it
    }
}