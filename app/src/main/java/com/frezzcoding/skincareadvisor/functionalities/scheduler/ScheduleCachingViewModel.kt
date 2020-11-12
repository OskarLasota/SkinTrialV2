package com.frezzcoding.skincareadvisor.functionalities.scheduler

import androidx.lifecycle.ViewModel
import com.frezzcoding.skincareadvisor.di.Injectable
import com.frezzcoding.skincareadvisor.functionalities.home.HomeRepository
import javax.inject.Inject

//this needs to have the interface of the repo
class ScheduleCachingViewModel @Inject constructor(private val repository : ScheduleRepositoryImpl) : ViewModel(){

    fun init(){
        println("here")
    }

}