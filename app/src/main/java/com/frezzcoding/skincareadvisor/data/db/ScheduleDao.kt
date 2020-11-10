package com.frezzcoding.skincareadvisor.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.frezzcoding.skincareadvisor.data.Schedule

@Dao
interface ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(schedule : Schedule)

    @Query("DELETE FROM schedule_table")
    fun deleteAllSchedules()

    @Query("UPDATE schedule_table SET checked = :checked, title = :title, hour = :hour, min = :min, monday = :monday, tuesday = :tuesday, wednesday = :wed, thursday =:thu, friday =:fri, saturday =:sat, sunday =:sun WHERE id = :id")
    fun update(id : Int, checked : Boolean, title : String, hour : Int, min : Int, monday : Boolean, tuesday : Boolean, wed : Boolean, thu : Boolean, fri : Boolean, sat : Boolean, sun : Boolean)

    @Query("select * from schedule_table ORDER BY hour ASC")
    fun getSchedules() : List<Schedule>

    @Query("DELETE FROM schedule_table WHERE id = :id")
    fun removeSchedule(id : Int)


}