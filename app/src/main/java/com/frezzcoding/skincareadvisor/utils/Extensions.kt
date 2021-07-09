package com.frezzcoding.skincareadvisor.utils

import com.frezzcoding.skincareadvisor.common.AppConstants.FRIDAY
import com.frezzcoding.skincareadvisor.common.AppConstants.MONDAY
import com.frezzcoding.skincareadvisor.common.AppConstants.SATURDAY
import com.frezzcoding.skincareadvisor.common.AppConstants.SUNDAY
import com.frezzcoding.skincareadvisor.common.AppConstants.THURSDAY
import com.frezzcoding.skincareadvisor.common.AppConstants.TUESDAY
import com.frezzcoding.skincareadvisor.common.AppConstants.WEDNESDAY
import com.frezzcoding.skincareadvisor.data.Schedule
import java.util.*
import java.util.concurrent.TimeUnit


fun getDisplayMinutes() : Array<String>{
    return arrayOf("00","01","02","03","04","05","06","07","08",
        "09","10","11","12","13","14","15","16","17","18",
        "19","20","21","22","23","24","25","26","27","28",
        "29","30","31","32", "33","34","35","36","37","38",
        "39","40","41","42","43","44","45","46","47","48",
        "49","50","51","52","53","54","55","56","57","58","59")
}

fun getDisplayHours() : Array<String>{
    return arrayOf("00","01","02","03","04","05","06","07","08", "09","10","11","12","13","14","15","16","17","18","19","20","21","22","23")
}


fun getNextNotificationDay(schedule : Schedule, day : String) : Long {
    val calendar = Calendar.getInstance()
    when(day){
        MONDAY -> {
            if (schedule.tuesday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(1))
            }
            if (schedule.wednesday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(2))
            }
            if (schedule.thursday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(3))
            }
            if (schedule.friday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(4))
            }
            if (schedule.saturday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(5))
            }
            if (schedule.sunday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(6))
            }
            if (schedule.monday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(7))
            }
        }
        TUESDAY -> {
            if (schedule.wednesday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(1))
            }
            if (schedule.thursday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(2))
            }
            if (schedule.friday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(3))
            }
            if (schedule.saturday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(4))
            }
            if (schedule.sunday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(5))
            }
            if (schedule.monday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(6))
            }
            if (schedule.tuesday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(7))
            }
        }
        WEDNESDAY -> {
            if (schedule.thursday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(1))
            }
            if (schedule.friday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(2))
            }
            if (schedule.saturday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(3))
            }
            if (schedule.sunday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(4))
            }
            if (schedule.monday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(5))
            }
            if (schedule.tuesday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(6))
            }
            if (schedule.wednesday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(7))
            }
        }
        THURSDAY -> {
            if (schedule.friday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(1))
            }
            if (schedule.saturday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(2))
            }
            if (schedule.sunday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(3))
            }
            if (schedule.monday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(4))
            }
            if (schedule.tuesday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(5))
            }
            if (schedule.wednesday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(6))
            }
            if (schedule.thursday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(7))
            }
        }
        FRIDAY -> {
            if (schedule.saturday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(1))
            }
            if (schedule.sunday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(2))
            }
            if (schedule.monday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(3))
            }
            if (schedule.tuesday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(4))
            }
            if (schedule.wednesday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(5))
            }
            if (schedule.thursday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(6))
            }
            if (schedule.friday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(7))
            }
        }
        SATURDAY -> {
            if (schedule.sunday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(1))
            }
            if (schedule.monday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(2))
            }
            if (schedule.tuesday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(3))
            }
            if (schedule.wednesday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(4))
            }
            if (schedule.thursday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(5))
            }
            if (schedule.friday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(6))
            }
            if (schedule.saturday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(7))
            }
        }
        SUNDAY -> {
            if (schedule.monday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(1))
            }
            if (schedule.tuesday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(2))
            }
            if (schedule.wednesday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(3))
            }
            if (schedule.thursday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(4))
            }
            if (schedule.friday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(5))
            }
            if (schedule.saturday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(6))
            }
            if (schedule.sunday) {
                return calendar.timeInMillis + (TimeUnit.DAYS.toMillis(7))
            }
        }
    }
    return 0
}

