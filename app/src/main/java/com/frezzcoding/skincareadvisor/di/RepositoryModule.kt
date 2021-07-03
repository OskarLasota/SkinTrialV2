package com.frezzcoding.skincareadvisor.di

import com.frezzcoding.skincareadvisor.functionalities.scheduler.ScheduleRepository
import com.frezzcoding.skincareadvisor.functionalities.scheduler.ScheduleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindScheduleRepo(scheduleRepositoryImpl: ScheduleRepositoryImpl): ScheduleRepository

}