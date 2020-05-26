package com.dmt.juniortask.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dmt.juniortask.AppApplication
import com.dmt.juniortask.R
import com.dmt.juniortask.network.UserProperty
import com.dmt.juniortask.repository.AppRepository
import com.dmt.juniortask.utils.hasInternet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repo: AppRepository, application : AppApplication) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
    get() = _response

    //Not sure if it's good to expose MutableLive data
    val username = MutableLiveData("")
    val password : MutableLiveData<String> = MutableLiveData("")

    private val _navigateToServers = MutableLiveData<String>()
    val navigateToServers
        get() = _navigateToServers

    fun onServersNavigated() {
        _navigateToServers.value = null
    }

    private val _isLoading = MutableLiveData(false)
    val isLoading
        get() = _isLoading

    private val res = getApplication<Application>().resources

    fun login() {
        _response.value = ""
        val username = username.value!!
        val password = password.value!!
        if(username.isBlank() || password.isBlank()) {
            _response.value = res.getString(R.string.incorrect_user_or_pass)
            return
        }

        val user = UserProperty(username, password)
        _isLoading.value = true
        coroutineScope.launch {
            if(hasInternet()) {
                try {
                    val tokenProp =  repo.login(user)
                    _navigateToServers.value = tokenProp.token
                    _response.value = res.getString(R.string.success)
                } catch (e: Exception) {
                    _response.value = res.getString(R.string.incorrect_user_or_pass)
                }
            } else {
                _response.value = res.getString(R.string.no_internet)
            }
            _isLoading.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}