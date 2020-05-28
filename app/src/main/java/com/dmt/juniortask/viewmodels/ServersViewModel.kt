package com.dmt.juniortask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmt.juniortask.domain.Server
import com.dmt.juniortask.repository.AppRepository
import com.dmt.juniortask.utils.hasInternet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ServersViewModel (private val repo: AppRepository, private val token: String) : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    private val _navigateToServerDetails = MutableLiveData<Long>()
    val navigateToServerDetails
        get() = _navigateToServerDetails

    //var servers : LiveData<List<Server>> = repo.servers
    private val _servers = MutableLiveData<List<Server>>()
    val servers : LiveData<List<Server>>
        get() = _servers

    fun onServerItemClicked(id: Long){
        _navigateToServerDetails.value = id
    }

    fun onServerDetailsNavigated() {
        _navigateToServerDetails.value = null
    }

    private val _isLoading = MutableLiveData(true)
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    init {
        coroutineScope.launch {
            if(hasInternet()){
                repo.refreshServers(token)
            }
            _servers.value = repo.getServers()
            _isLoading.value = false
        }
    }



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}