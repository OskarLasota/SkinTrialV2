package com.frezzcoding.skincareadvisor.di.modules

import com.frezzcoding.skincareadvisor.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}