package com.dmt.juniortask.di.login

import androidx.lifecycle.ViewModel
import com.dmt.juniortask.di.ViewModelKey
import com.dmt.juniortask.viewmodels.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(myViewModel: LoginViewModel): ViewModel

}