package com.frezzcoding.skincareadvisor.functionalities.scheduler

import com.frezzcoding.skincareadvisor.data.Schedule

interface ScheduleRepository {
    fun getSchedules() : List<Schedule>
    fun insertSchedule(schedule: Schedule)
    fun removeSchedule(schedule: Schedule)
    fun getNextScheduleId() : Int
    fun updateSchedule(schedule: Schedule)
}