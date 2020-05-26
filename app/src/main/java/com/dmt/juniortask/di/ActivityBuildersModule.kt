package com.dmt.juniortask.di

import com.dmt.juniortask.di.login.LoginViewModelModule
import com.dmt.juniortask.di.main.MainFragmentBuildersModule
import com.dmt.juniortask.di.main.MainViewModelsModule
import com.dmt.juniortask.ui.LoginActivity
import com.dmt.juniortask.ui.MainActivity
import dagger.Module

import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [
        LoginViewModelModule::class
    ])
    abstract fun contributeLoginActivity(): LoginActivity


    @ContributesAndroidInjector(modules = [
        MainViewModelsModule::class,
        MainFragmentBuildersModule::class
    ])
    abstract fun contributeMainActivity(): MainActivity
}