package com.frezzcoding.skincareadvisor.functionalities.scheduler

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.frezzcoding.skincareadvisor.R
import com.frezzcoding.skincareadvisor.data.Schedule
import com.frezzcoding.skincareadvisor.databinding.EditscheduleFragmentBinding
import com.frezzcoding.skincareadvisor.di.Injectable
import com.frezzcoding.skincareadvisor.utils.NotificationBroadcast
import java.util.*
import javax.inject.Inject

class EditScheduleFragment : Fragment(), Injectable {

    private lateinit var binding : EditscheduleFragmentBinding
    private lateinit var schedule : Schedule
    private var edit : Boolean = false

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ScheduleCachingViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.editschedule_fragment, container, false
        )
        setListeners()
        setProductValues()
        init()

        /*
         TODO refactor this code into extension functions
         */

        return binding.root
    }

    private fun setProductValues(){
        if(requireArguments().get("schedule") != null) {
            schedule = requireArguments().get("schedule") as Schedule
            binding.schedule = schedule
            if(schedule.id != 0) {
                edit = true
                if(schedule.monday){
                    binding.tvMonday.backgroundTintList = binding.root.resources.getColorStateList(R.color.greyish)
                }
                if(schedule.tuesday){
                    binding.tvTuesday.backgroundTintList =  binding.root.resources.getColorStateList(R.color.greyish)
                }
                if(schedule.wednesday){
                    binding.tvWednesday.backgroundTintList =  binding.root.resources.getColorStateList(R.color.greyish)
                }
                if(schedule.thursday){
                    binding.tvThursday.backgroundTintList =  binding.root.resources.getColorStateList(R.color.greyish)
                }
                if(schedule.friday){
                    binding.tvFriday.backgroundTintList =  binding.root.resources.getColorStateList(R.color.greyish)
                }
                if(schedule.saturday){
                    binding.tvSaturday.backgroundTintList =  binding.root.resources.getColorStateList(R.color.greyish)
                }
                if(schedule.sunday){
                    binding.tvSunday.backgroundTintList =  binding.root.resources.getColorStateList(R.color.greyish)
                }

            }
        }
    }

    private fun init(){
        binding.timePickerStartHour.minValue = 0
        binding.timePickerStartHour.maxValue = 23
        binding.timePickerStartHour.displayedValues = arrayOf("00","01","02","03","04","05","06","07","08", "09","10","11","12","13","14","15","16","17","18","19","20","21","22","23")
        binding.timePickerStartMin.minValue = 0
        binding.timePickerStartMin.maxValue = 59
        binding.timePickerStartMin.displayedValues = arrayOf("00","01","02","03","04","05","06","07","08",
            "09","10","11","12","13","14","15","16","17","18",
            "19","20","21","22","23","24","25","26","27","28",
            "29","30","31","32", "33","34","35","36","37","38",
            "39","40","41","42","43","44","45","46","47","48",
            "49","50","51","52","53","54","55","56","57","58","59")
    }

    private fun validationPasses() : Boolean{
        if(binding.etTitle.text.toString().length < 2){
            Toast.makeText(this.context, "Digite o nome do produto!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }




    private fun setListeners(){
        binding.btnSave.setOnClickListener {
            if(validationPasses()){
                if(!edit){
                    var tempid: Int = (Math.random() * 15340).toInt()
                    schedule.id = tempid
                    schedule.checked = true
                }
                schedule.title = binding.etTitle.text.toString()
                schedule.hour = binding.timePickerStartHour.value
                schedule.min = binding.timePickerStartMin.value

                var time = getNextNotification()

                if(time != 0L){
                    var inte = Intent(context, NotificationBroadcast::class.java)
                    inte.putExtra("key", schedule.id)
                    val pi = PendingIntent.getBroadcast(
                        context,
                        schedule.id,
                        inte,
                        PendingIntent.FLAG_UPDATE_CURRENT
                    )
                    val am =
                        requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pi)
                }
                if (edit) {
                    viewModel.updateSchedule(schedule)
                } else {
                    viewModel.insertSchedule(schedule)
                }
                Navigation.findNavController(binding.root).navigate(R.id.schedulerFragment)
            }
        }
        binding.btnCancel.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.schedulerFragment)
        }

        //listeners for each day button
        binding.tvMonday.setOnClickListener {
            if(schedule.monday){
                binding.tvMonday.backgroundTintList = it.resources.getColorStateList(R.color.miss)
                schedule.monday = false
            }else{
                binding.tvMonday.backgroundTintList = it.resources.getColorStateList(R.color.greyish)
                schedule.monday = true
            }
        }
        binding.tvTuesday.setOnClickListener {
            if(schedule.tuesday){
                binding.tvTuesday.backgroundTintList = it.resources.getColorStateList(R.color.miss)
                schedule.tuesday = false
            }else{
                binding.tvTuesday.backgroundTintList = it.resources.getColorStateList(R.color.greyish)
                schedule.tuesday = true
            }
        }
        binding.tvWednesday.setOnClickListener {
            if(schedule.wednesday){
                binding.tvWednesday.backgroundTintList = it.resources.getColorStateList(R.color.miss)
                schedule.wednesday = false
            }else{
                binding.tvWednesday.backgroundTintList = it.resources.getColorStateList(R.color.greyish)
                schedule.wednesday = true
            }
        }
        binding.tvThursday.setOnClickListener {
            if(schedule.thursday){
                binding.tvThursday.backgroundTintList = it.resources.getColorStateList(R.color.miss)
                schedule.thursday = false
            }else{
                binding.tvThursday.backgroundTintList = it.resources.getColorStateList(R.color.greyish)
                schedule.thursday = true
            }
        }
        binding.tvFriday.setOnClickListener {
            if(schedule.friday){
                binding.tvFriday.backgroundTintList = it.resources.getColorStateList(R.color.miss)
                schedule.friday = false
            }else{
                binding.tvFriday.backgroundTintList = it.resources.getColorStateList(R.color.greyish)
                schedule.friday = true
            }
        }
        binding.tvSaturday.setOnClickListener {
            if(schedule.saturday){
                binding.tvSaturday.backgroundTintList = it.resources.getColorStateList(R.color.miss)
                schedule.saturday = false
            }else{
                binding.tvSaturday.backgroundTintList = it.resources.getColorStateList(R.color.greyish)
                schedule.saturday = true
            }
        }
        binding.tvSunday.setOnClickListener {
            if(schedule.sunday){
                binding.tvSunday.backgroundTintList = it.resources.getColorStateList(R.color.miss)
                schedule.sunday = false
            }else{
                binding.tvSunday.backgroundTintList = it.resources.getColorStateList(R.color.greyish)
                schedule.sunday = true
            }
        }


    }

    private fun getNextNotification() : Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, schedule.hour)
        calendar.set(Calendar.MINUTE, schedule.hour)

        if(calendar.time.toString().substring(0,3) == "Mon"){
            val current = Calendar.getInstance()
            val calendar1 = Calendar.getInstance()
            calendar1.set(Calendar.HOUR_OF_DAY, schedule.hour)
            calendar1.set(Calendar.MINUTE, schedule.min)
            for(i in 0..7){
                if(current.timeInMillis > calendar1.timeInMillis){
                    calendar1.add(Calendar.DATE, 1)
                    continue
                }
                when(i){
                    0-> if(schedule.monday) {return calendar1.timeInMillis}
                    1-> if(schedule.tuesday) {return calendar1.timeInMillis}
                    2-> if(schedule.wednesday) {return calendar1.timeInMillis}
                    3-> if(schedule.thursday) {return calendar1.timeInMillis}
                    4-> if(schedule.friday) {return calendar1.timeInMillis}
                    5-> if(schedule.saturday) {return calendar1.timeInMillis}
                    6-> if(schedule.sunday) {return calendar1.timeInMillis}
                }
                calendar1.add(Calendar.DATE, 1)
            }
        }
        if(calendar.time.toString().substring(0,3) == "Tue"){
            val current = Calendar.getInstance()
            val calendar1 = Calendar.getInstance()
            calendar1.set(Calendar.HOUR_OF_DAY, schedule.hour)
            calendar1.set(Calendar.MINUTE, schedule.min)
            for(i in 0..7){
                if(current.timeInMillis > calendar1.timeInMillis){
                    calendar1.add(Calendar.DATE, 1)
                    continue
                }
                when(i){
                    0-> if(schedule.tuesday) {return calendar1.timeInMillis}
                    1-> if(schedule.wednesday) {return calendar1.timeInMillis}
                    2-> if(schedule.thursday) {return calendar1.timeInMillis}
                    3-> if(schedule.friday) {return calendar1.timeInMillis}
                    4-> if(schedule.saturday) {return calendar1.timeInMillis}
                    5-> if(schedule.sunday) {return calendar1.timeInMillis}
                    6-> if(schedule.monday) {return calendar1.timeInMillis}
                }
                calendar1.add(Calendar.DATE, 1)
            }
        }
        if(calendar.time.toString().substring(0,3) == "Wed"){
            val current = Calendar.getInstance()
            val calendar1 = Calendar.getInstance()
            calendar1.set(Calendar.HOUR_OF_DAY, schedule.hour)
            calendar1.set(Calendar.MINUTE, schedule.min)
            for(i in 0..7){
                if(current.timeInMillis > calendar1.timeInMillis){
                    calendar1.add(Calendar.DATE, 1)
                    continue
                }
                when(i){
                    0-> if(schedule.wednesday) {return calendar1.timeInMillis}
                    1-> if(schedule.thursday) { return calendar1.timeInMillis}
                    2-> if(schedule.friday) {return calendar1.timeInMillis}
                    3-> if(schedule.saturday) {return calendar1.timeInMillis}
                    4-> if(schedule.sunday) {return calendar1.timeInMillis}
                    5-> if(schedule.monday) {return calendar1.timeInMillis}
                    6-> if(schedule.tuesday) {return calendar1.timeInMillis}
                }
                calendar1.add(Calendar.DATE, 1)
            }
        }
        if(calendar.time.toString().substring(0,3) == "Thu"){
            val current = Calendar.getInstance()
            val calendar1 = Calendar.getInstance()
            calendar1.set(Calendar.HOUR_OF_DAY, schedule.hour)
            calendar1.set(Calendar.MINUTE, schedule.min)
            for(i in 0..7){
                if(current.timeInMillis > calendar1.timeInMillis){
                    calendar1.add(Calendar.DATE, 1)
                    continue
                }
                println("i : "+i)
                println(calendar1.time)
                when(i){
                    0-> if(schedule.thursday) {return calendar1.timeInMillis}
                    1-> if(schedule.friday) {return calendar1.timeInMillis}
                    2-> if(schedule.saturday) {return calendar1.timeInMillis}
                    3-> if(schedule.sunday) {return calendar1.timeInMillis}
                    4-> if(schedule.monday) {return calendar1.timeInMillis}
                    5-> if(schedule.tuesday) {return calendar1.timeInMillis}
                    6-> if(schedule.wednesday) {return calendar1.timeInMillis}
                }
                calendar1.add(Calendar.DATE, 1)
            }
        }
        if(calendar.time.toString().substring(0,3) == "Fri"){
            val current = Calendar.getInstance()
            val calendar1 = Calendar.getInstance()
            calendar1.set(Calendar.HOUR_OF_DAY, schedule.hour)
            calendar1.set(Calendar.MINUTE, schedule.min)
            for(i in 0..7){
                if(current.timeInMillis > calendar1.timeInMillis){
                    calendar1.add(Calendar.DATE, 1)
                    continue
                }
                when(i){
                    0-> if(schedule.friday) {return calendar1.timeInMillis}
                    1-> if(schedule.saturday) {return calendar1.timeInMillis}
                    2-> if(schedule.sunday) {return calendar1.timeInMillis}
                    3-> if(schedule.monday) {return calendar1.timeInMillis}
                    4-> if(schedule.tuesday) {return calendar1.timeInMillis}
                    5-> if(schedule.wednesday) {return calendar1.timeInMillis}
                    6-> if(schedule.thursday) {return calendar1.timeInMillis}
                }
                calendar1.add(Calendar.DATE, 1)
            }
        }
        if(calendar.time.toString().substring(0,3) == "Sat"){
            val current = Calendar.getInstance()
            val calendar1 = Calendar.getInstance()
            calendar1.set(Calendar.HOUR_OF_DAY, schedule.hour)
            calendar1.set(Calendar.MINUTE, schedule.min)
            for(i in 0..7){
                if(current.timeInMillis > calendar1.timeInMillis){
                    calendar1.add(Calendar.DATE, 1)
                    continue
                }
                when(i){
                    0-> if(schedule.saturday) {return calendar1.timeInMillis}
                    1-> if(schedule.sunday) {return calendar1.timeInMillis}
                    2-> if(schedule.monday) {return calendar1.timeInMillis}
                    3-> if(schedule.tuesday) {return calendar1.timeInMillis}
                    4-> if(schedule.wednesday) {return calendar1.timeInMillis}
                    5-> if(schedule.thursday) {return calendar1.timeInMillis}
                    6-> if(schedule.friday) {return calendar1.timeInMillis}
                }
                calendar1.add(Calendar.DATE, 1)
            }
        }
        if(calendar.time.toString().substring(0,3) == "Sun"){
            val current = Calendar.getInstance()
            val calendar1 = Calendar.getInstance()
            calendar1.set(Calendar.HOUR_OF_DAY, schedule.hour)
            calendar1.set(Calendar.MINUTE, schedule.min)
            for(i in 0..7){
                if(current.timeInMillis > calendar1.timeInMillis){
                    calendar1.add(Calendar.DATE, 1)
                    continue
                }
                when(i){
                    0-> if(schedule.sunday) {return calendar1.timeInMillis}
                    1-> if(schedule.monday) {return calendar1.timeInMillis}
                    2-> if(schedule.tuesday) {return calendar1.timeInMillis}
                    3-> if(schedule.wednesday) {return calendar1.timeInMillis}
                    4-> if(schedule.thursday) {return calendar1.timeInMillis}
                    5-> if(schedule.friday) {return calendar1.timeInMillis}
                    6-> if(schedule.saturday) {return calendar1.timeInMillis}
                }
                calendar1.add(Calendar.DATE, 1)
            }
        }
        return 0
    }


}