package com.dmt.juniortask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmt.juniortask.domain.Server
import com.dmt.juniortask.repository.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ServerDetailViewModel (private val serverId : Long, private val repo : AppRepository) : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    private val _server = MutableLiveData<Server>()
    val server : LiveData<Server>
    get() = _server

    init{
        coroutineScope.launch {
            _server.value = repo.getServerById(serverId)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}