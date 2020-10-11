package com.frezzcoding.skincareadvisor.di.components

import com.frezzcoding.skincareadvisor.common.App
import com.frezzcoding.skincareadvisor.di.modules.ApiModule
import com.frezzcoding.skincareadvisor.di.modules.MainModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [MainModule::class, ApiModule::class])
interface AppComponent {
    fun inject(application: App)
}