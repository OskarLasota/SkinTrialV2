package com.frezzcoding.skincareadvisor.di

import com.frezzcoding.skincareadvisor.functionalities.account.AccountFragment
import com.frezzcoding.skincareadvisor.functionalities.home.HomeFragment
import com.frezzcoding.skincareadvisor.functionalities.login.LoginFragment
import com.frezzcoding.skincareadvisor.functionalities.routines.RoutineFragment
import com.frezzcoding.skincareadvisor.functionalities.scheduler.EditScheduleFragment
import com.frezzcoding.skincareadvisor.functionalities.scheduler.SchedulerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeAccountFragment(): AccountFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeRoutineFragment(): RoutineFragment

    @ContributesAndroidInjector
    abstract fun contributeSchedulerFragment(): SchedulerFragment

    @ContributesAndroidInjector
    abstract fun contributeEditScheduleFragment(): EditScheduleFragment



}