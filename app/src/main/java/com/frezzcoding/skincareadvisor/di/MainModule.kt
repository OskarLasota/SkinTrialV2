package com.frezzcoding.skincareadvisor.di

import com.frezzcoding.skincareadvisor.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class MainModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}