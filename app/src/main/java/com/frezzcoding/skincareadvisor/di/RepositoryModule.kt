package com.frezzcoding.skincareadvisor.di

import com.frezzcoding.skincareadvisor.functionalities.scheduler.ScheduleRepository
import com.frezzcoding.skincareadvisor.functionalities.scheduler.ScheduleRepositoryImpl
import dagger.Binds
import dagger.Module


@Module(includes = [RepositoryModule.RepoBindings::class])
abstract class RepositoryModule {

    @Module
    interface RepoBindings {
        @Binds
        fun bindScheduleRepo(scheduleRepositoryImpl: ScheduleRepositoryImpl) : ScheduleRepository
    }

}