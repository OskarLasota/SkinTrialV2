package com.frezzcoding.skincareadvisor.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.frezzcoding.skincareadvisor.common.vm.ViewModelFactory
import com.frezzcoding.skincareadvisor.functionalities.home.HomeViewModel
import com.frezzcoding.skincareadvisor.functionalities.scheduler.ScheduleCachingViewModel
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
    @IntoMap
    @ViewModelKey(ScheduleCachingViewModel::class)
    abstract fun bindScheduleCachingViewModel(scheduleViewModel: ScheduleCachingViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}