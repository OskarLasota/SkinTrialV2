package com.frezzcoding.skincareadvisor.functionalities.scheduler

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frezzcoding.skincareadvisor.data.Schedule
import com.frezzcoding.skincareadvisor.di.Injectable
import com.frezzcoding.skincareadvisor.functionalities.home.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//this needs to have the interface of the repo
class ScheduleCachingViewModel @Inject constructor(private val repository : ScheduleRepository) : ViewModel(){

    fun init(){

    }

    fun getSchedules() = viewModelScope.launch(Dispatchers.IO){
        repository.getSchedules()
    }

}