package com.dmt.juniortask.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dmt.juniortask.repository.AppRepository
import com.dmt.juniortask.viewmodels.ServersViewModel

class ServersViewModelFactory(private val repo: AppRepository, private val token : String)
    : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ServersViewModel::class.java)) {
            return ServersViewModel(repo, token) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}