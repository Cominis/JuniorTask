package com.dmt.juniortask

import com.dmt.juniortask.di.DaggerApplicationComponent
import com.dmt.juniortask.repository.UserManager
import com.dmt.juniortask.storage.SharedPreferencesStorage
import dagger.android.DaggerApplication

class AppApplication : DaggerApplication() {
    private val applicationInjector = DaggerApplicationComponent.builder()
        .application(this).context(this)
        .build()

    override fun applicationInjector() = applicationInjector

    val userManager by lazy {
        UserManager(SharedPreferencesStorage(this))
    }
}