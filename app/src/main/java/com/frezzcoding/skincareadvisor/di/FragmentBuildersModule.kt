package com.frezzcoding.skincareadvisor.di

import com.frezzcoding.skincareadvisor.functionalities.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

}