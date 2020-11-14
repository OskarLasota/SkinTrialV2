package com.frezzcoding.skincareadvisor.utils

import com.frezzcoding.skincareadvisor.R
import com.frezzcoding.skincareadvisor.data.Schedule
import com.frezzcoding.skincareadvisor.databinding.EditscheduleFragmentBinding

//is this testable?
fun Schedule.markSelectedDays(binding : EditscheduleFragmentBinding){
    if(this.id != 0) {
        if(this.monday){
            binding.tvMonday.backgroundTintList = binding.root.resources.getColorStateList(R.color.greyish)
        }
        if(this.tuesday){
            binding.tvTuesday.backgroundTintList =  binding.root.resources.getColorStateList(R.color.greyish)
        }
        if(this.wednesday){
            binding.tvWednesday.backgroundTintList =  binding.root.resources.getColorStateList(R.color.greyish)
        }
        if(this.thursday){
            binding.tvThursday.backgroundTintList =  binding.root.resources.getColorStateList(R.color.greyish)
        }
        if(this.friday){
            binding.tvFriday.backgroundTintList =  binding.root.resources.getColorStateList(R.color.greyish)
        }
        if(this.saturday){
            binding.tvSaturday.backgroundTintList =  binding.root.resources.getColorStateList(R.color.greyish)
        }
        if(this.sunday){
            binding.tvSunday.backgroundTintList =  binding.root.resources.getColorStateList(R.color.greyish)
        }

    }
}

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
