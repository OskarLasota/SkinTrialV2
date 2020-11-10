package com.frezzcoding.skincareadvisor.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="schedule_table")
class Schedule : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var checked : Boolean = true
    var title : String
    var hour : Int = 0
    var min : Int = 0
    var monday : Boolean = false
    var tuesday : Boolean = false
    var wednesday : Boolean = false
    var thursday : Boolean = false
    var friday : Boolean = false
    var saturday : Boolean = false
    var sunday : Boolean = false

    @Ignore
    constructor(id : Int,checked : Boolean, title : String, hour : Int, min : Int, monday : Boolean, tuesday : Boolean, wed : Boolean, thu : Boolean, fri : Boolean, sat : Boolean, sun : Boolean){
        this.id = id
        this.checked = checked
        this.title = title
        this.hour = hour
        this.min = min
        this.monday = monday
        this.tuesday = tuesday
        this.wednesday = wed
        this.thursday = thu
        this.friday = fri
        this.saturday = sat
        this.sunday = sun
    }

    constructor() : this(0, true,"", 0, 0, false, false, false, false, false, false, false)

}