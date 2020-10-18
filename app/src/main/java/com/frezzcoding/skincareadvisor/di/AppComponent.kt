package com.frezzcoding.skincareadvisor.di

import android.app.Application
import com.frezzcoding.skincareadvisor.App
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [MainModule::class, ApiModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
    fun inject(application: App)
}