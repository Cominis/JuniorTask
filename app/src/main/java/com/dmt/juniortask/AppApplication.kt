package com.dmt.juniortask

import com.dmt.juniortask.di.DaggerApplicationComponent
import dagger.android.DaggerApplication

class AppApplication : DaggerApplication() {
    private val applicationInjector = DaggerApplicationComponent.builder()
        .application(this)
        .build()

    override fun applicationInjector() = applicationInjector
}