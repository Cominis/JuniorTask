package com.dmt.juniortask.di

import androidx.lifecycle.ViewModelProvider
import com.dmt.juniortask.storage.SharedPreferencesStorage
import com.dmt.juniortask.storage.Storage
import com.dmt.juniortask.viewmodels.factories.DaggerViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class StorageModule {

    @Singleton
    @Binds
    abstract fun bindSharedPreferences(storage : SharedPreferencesStorage): Storage
}