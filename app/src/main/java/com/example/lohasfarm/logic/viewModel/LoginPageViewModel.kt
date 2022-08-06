package com.example.lohasfarm.logic.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lohasfarm.TOAST_TEST
import com.example.lohasfarm.logic.network.model.BaseModel
import com.example.lohasfarm.logic.network.model.LoginModel
import com.example.lohasfarm.logic.network.repository.AccountRepository
import com.example.lohasfarm.logic.utils.LfState
import com.example.lohasfarm.logic.utils.StateCode
import com.example.lohasfarm.ui.utils.showLongToast
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
    val password: LiveData<String> = _password

    private val _accountR = MutableLiveData("")
    val accountR: LiveData<String> = _accountR

    private val _passwordR = MutableLiveData("")
    val passwordR: LiveData<String> = _passwordR

    private val _passwordR2 = MutableLiveData("")
    val passwordR2: LiveData<String> = _passwordR2

    private val _pattern = MutableLiveData("")
    val pattern: LiveData<String> = _pattern

    private val accountRepository = AccountRepository()

    fun login() = viewModelScope.async(Dispatchers.IO) {
        if (TOAST_TEST) {
            withContext(Dispatchers.Main) {
                Log.i(TAG, _account.value!!)
                Log.i(TAG, _password.value!!)
            }
        }
        if (_account.value!!.isNotEmpty()
            && _password.value!!.isNotEmpty()
            && Regex("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+\$").matches(_account.value!!)
        ) {
            val loginModel: BaseModel<LoginModel> =
                accountRepository.login(_account.value!!.replace(" ",""),
                    _password.value!!.replace(" ",""))

            if (loginModel.code == StateCode.LoginSuccess.code) {
                Log.i(TAG, loginModel.content.uuid)
                LfState.isLogin = true
                LfState.saveLoginState(loginModel)
            } else {
                LfState.isLogin = false
            }

            withContext(Dispatchers.Main) {
                Log.i(TAG, loginModel.msg)
                Log.i(TAG, loginModel.code.toString())

                showToast(
                    getApplication(),
                    loginModel.msg
                )
            }
        } else {
            withContext(Dispatchers.Main) {
                showLongToast(
                    getApplication(),
                    "帐号或密码错误"
                )
            }
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
            }

            PASSWORDMODE.REGISTER.id -> {
                _passwordR.value = it
            }

            else -> {
                _passwordR2.value = it
            }
        }
    }

}