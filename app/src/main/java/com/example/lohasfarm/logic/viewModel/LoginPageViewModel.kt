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

enum class PASSWORDMODE(val id: Int) {
    LOGIN(1),
    REGISTER(2),
    REGISTER2(3)
}


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

    private val _passwordRaw = MutableLiveData("")
    val passwordRaw: LiveData<String> = _passwordRaw

    private val _accountR = MutableLiveData("")
    val accountR: LiveData<String> = _accountR

    private val _passwordR = MutableLiveData("")

    private val _passwordRRaw = MutableLiveData("")
    val passwordRRaw: LiveData<String> = _passwordRRaw

    private val _passwordR2 = MutableLiveData("")

    private val _passwordR2Raw = MutableLiveData("")
    val passwordR2Raw: LiveData<String> = _passwordR2Raw

    private val _pattern = MutableLiveData("")
    val pattern: LiveData<String> = _pattern

    private val accountRepository = AccountRepository()

    fun login() = viewModelScope.async(Dispatchers.IO) {
        val loginModel: BaseModel<LoginModel> = accountRepository.login(_account.value!!, _password.value!!)
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

    fun register() {
        showToast(
            getApplication(),
            "注册功能暂未开放"
        )
    }

    fun updateAccount(it: String) {
        if (_loginState.value!!) {
            _account.value = it
        } else {
            _accountR.value = it
        }
    }

    fun updatePattern(it: String) {
        _pattern.value = it
    }

    fun updatePassword(tag: Int, it: String) {
        when (tag) {
            PASSWORDMODE.LOGIN.id -> {
                _password.value = it
                _passwordRaw.value = if (_password.value!!.isNotEmpty()) {
                    "*".repeat(_password.value!!.length)
                } else {
                    ""
                }
            }

            PASSWORDMODE.REGISTER.id -> {
                _passwordR.value = it
                _passwordRRaw.value = if (_passwordR.value!!.isNotEmpty()) {
                    "*".repeat(_passwordR.value!!.length)
                } else {
                    ""
                }
            }

            else -> {
                _passwordR2.value = it
                _passwordR2Raw.value = if (_passwordR2.value!!.isNotEmpty()) {
                    "*".repeat(_passwordR2.value!!.length)
                } else {
                    ""
                }
            }
        }
    }

}