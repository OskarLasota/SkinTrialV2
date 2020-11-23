package com.frezzcoding.skincareadvisor.functionalities.scheduler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frezzcoding.skincareadvisor.data.Curiosity
import com.frezzcoding.skincareadvisor.data.Schedule
import com.frezzcoding.skincareadvisor.di.Injectable
import com.frezzcoding.skincareadvisor.functionalities.home.HomeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class ScheduleCachingViewModel @Inject constructor(private val repository : ScheduleRepository) : ViewModel(){

    private val _maxId = MutableLiveData<Int>()
    val maxId : LiveData<Int> = _maxId

    private val _schedules = MutableLiveData<List<Schedule>>()
    val schedules : LiveData<List<Schedule>> = _schedules

    init{
        CoroutineScope(Dispatchers.IO).launch {
            _maxId.postValue(repository.getNextScheduleId())
        }
    }

    fun updateSchedule(schedule: Schedule) = viewModelScope.launch(Dispatchers.IO){
        repository.updateSchedule(schedule)
    }

    fun insertSchedule(schedule: Schedule) = viewModelScope.launch(Dispatchers.IO){
        repository.insertSchedule(schedule)
    }

    fun getSchedules() = viewModelScope.launch(Dispatchers.IO){
        _schedules.postValue(repository.getSchedules())
    }



}