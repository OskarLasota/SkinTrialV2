package com.frezzcoding.skincareadvisor.functionalities.scheduler

import com.frezzcoding.skincareadvisor.data.Schedule
import javax.inject.Inject

interface ScheduleRepository {
    fun getSchedules() : List<Schedule>
    fun insertSchedule(schedule: Schedule)

}