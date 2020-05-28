package com.dmt.juniortask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {
    private val _title = MutableLiveData("")
    val title : LiveData<String>
    get() = _title

    fun setTitle(title: String) {
        _title.value = title
    }
}