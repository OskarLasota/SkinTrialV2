package com.frezzcoding.skincareadvisor.functionalities.scheduler

import android.app.Application
import com.frezzcoding.skincareadvisor.data.Schedule
import com.frezzcoding.skincareadvisor.data.db.AppDatabase
import com.frezzcoding.skincareadvisor.data.db.ScheduleDao
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(application: Application) : ScheduleRepository {

    private var scheduleDao : ScheduleDao = AppDatabase.getInstance(application).scheduleDao()

    override fun getSchedules() = scheduleDao.getSchedules()

    override fun insertSchedule(schedule: Schedule) {
        scheduleDao.insert(schedule)
    }

    override fun removeSchedule(schedule: Schedule) {
        scheduleDao.removeSchedule(schedule.id)
    }

    override fun getNextScheduleId() : Int {
        return scheduleDao.getMaxScheduleId() + 1
    }

    override fun updateSchedule(schedule: Schedule) {
        scheduleDao.update(schedule.id, schedule.checked, schedule.title, schedule.hour, schedule.min, schedule.monday, schedule.tuesday, schedule.wednesday, schedule.thursday, schedule.friday, schedule.saturday, schedule.sunday)
    }


}