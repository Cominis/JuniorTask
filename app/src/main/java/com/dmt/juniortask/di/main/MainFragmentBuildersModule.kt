package com.dmt.juniortask.di.main

import com.dmt.juniortask.ui.ServerDetailFragment
import com.dmt.juniortask.ui.ServersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeServersFragment(): ServersFragment

    @ContributesAndroidInjector
    abstract fun contributeServerDetailFragment(): ServerDetailFragment
}