package com.dmt.juniortask.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dmt.juniortask.repository.AppRepository
import com.dmt.juniortask.viewmodels.ServerDetailViewModel

class ServerDetailViewModelFactory(
    private val serverId: Long,
    private val repo: AppRepository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ServerDetailViewModel::class.java)) {
            return ServerDetailViewModel(
                serverId,
                repo
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}