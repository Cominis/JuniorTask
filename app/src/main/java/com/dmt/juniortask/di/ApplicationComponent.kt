package com.dmt.juniortask.di

import android.content.Context
import com.dmt.juniortask.AppApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuildersModule::class,
    ViewModelFactoryModule::class,
    ApplicationModule::class,
    StorageModule::class
])
interface ApplicationComponent : AndroidInjector<AppApplication> {
    override fun inject(application: AppApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: AppApplication): Builder
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): ApplicationComponent
    }
}

