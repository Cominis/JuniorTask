package com.dmt.juniortask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmt.juniortask.network.UserProperty
import com.dmt.juniortask.repository.AppRepository
import com.dmt.juniortask.repository.UserManager
import com.dmt.juniortask.ui.LoginError
import com.dmt.juniortask.ui.LoginSuccess
import com.dmt.juniortask.ui.LoginViewState
import com.dmt.juniortask.utils.hasInternet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repo: AppRepository, private val userManager : UserManager) : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    //Not sure if it's good to expose MutableLive data
    val username = MutableLiveData("")
    val password : MutableLiveData<String> = MutableLiveData("")

    private val _loginState = MutableLiveData<LoginViewState>()
    val loginState: LiveData<LoginViewState>
        get() = _loginState

    private val _isLoading = MutableLiveData(false)
    val isLoading
        get() = _isLoading

    fun login() {
        val username = username.value!!
        val password = password.value!!
        if(username.isBlank() || password.isBlank()) {
            _loginState.value = LoginError
            return
        }

        val user = UserProperty(username, password)
        _isLoading.value = true
        coroutineScope.launch {
            if(hasInternet()) {
                try {
                    val tokenProp =  repo.login(user)

                    userManager.loginUser(tokenProp.token)
                    _loginState.value = LoginSuccess
                } catch (e: Exception) {
                    _loginState.value = LoginError
                }
            } else {
                _loginState.value = LoginError
            }
            _isLoading.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}