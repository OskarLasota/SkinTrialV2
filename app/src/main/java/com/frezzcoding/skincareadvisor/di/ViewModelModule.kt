package com.frezzcoding.skincareadvisor.di

import androidx.lifecycle.ViewModel
import com.frezzcoding.skincareadvisor.functionalities.home.HomeViewModel
import com.frezzcoding.skincareadvisor.functionalities.scheduler.ScheduleCachingViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(userViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScheduleCachingViewModel::class)
    abstract fun bindScheduleCachingViewModel(scheduleViewModel: ScheduleCachingViewModel): ViewModel

}