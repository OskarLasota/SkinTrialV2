package com.frezzcoding.skincareadvisor.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.frezzcoding.skincareadvisor.common.vm.ViewModelFactory
import com.frezzcoding.skincareadvisor.functionalities.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(userViewModel: HomeViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}