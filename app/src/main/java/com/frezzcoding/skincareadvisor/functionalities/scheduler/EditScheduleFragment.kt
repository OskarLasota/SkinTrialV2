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
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import com.frezzcoding.skincareadvisor.R
import com.frezzcoding.skincareadvisor.data.Schedule
import com.frezzcoding.skincareadvisor.databinding.EditscheduleFragmentBinding
import com.frezzcoding.skincareadvisor.utils.NotificationBroadcast
import com.frezzcoding.skincareadvisor.utils.getDisplayHours
import com.frezzcoding.skincareadvisor.utils.getDisplayMinutes
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class EditScheduleFragment : Fragment() {

    private lateinit var binding : EditscheduleFragmentBinding
    private lateinit var schedule : Schedule
    private var edit : Boolean = false
    private var maxScheduleId : Int = 0

    private val viewModel by viewModels<ScheduleCachingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.editschedule_fragment, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setProductValues()
        init()
        registerObservers()
    }

    private fun registerObservers(){
        viewModel.maxId.observe(viewLifecycleOwner) {
            maxScheduleId = it
        }
    }

    private fun setProductValues(){
        if(requireArguments().get("schedule") != null) {
            schedule = requireArguments().get("schedule") as Schedule
            binding.schedule = schedule
            schedule.let{
            if(schedule.id != 0) {
                edit = true
                    if (schedule.monday) {
                        binding.tvMonday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.greyish)
                    }
                    if (schedule.tuesday) {
                        binding.tvTuesday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.greyish)
                    }
                    if (schedule.wednesday) {
                        binding.tvWednesday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.greyish)
                    }
                    if (schedule.thursday) {
                        binding.tvThursday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.greyish)
                    }
                    if (schedule.friday) {
                        binding.tvFriday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.greyish)
                    }
                    if (schedule.saturday) {
                        binding.tvSaturday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.greyish)
                    }
                    if (schedule.sunday) {
                        binding.tvSunday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.greyish)
                    }
                }

            }
        }
    }

    private fun init(){
        binding.timePickerStartHour.minValue = 0
        binding.timePickerStartHour.maxValue = 23
        binding.timePickerStartHour.displayedValues = getDisplayHours() //extension function
        binding.timePickerStartMin.minValue = 0
        binding.timePickerStartMin.maxValue = 59
        binding.timePickerStartMin.displayedValues = getDisplayMinutes() //extension function
    }

    private fun validationPasses() : Boolean{
        if(binding.etTitle.text.toString().length < 2){
            Toast.makeText(this.context, R.string.empty_name_warning, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }


    //handle device time change
    private fun setListeners(){
        binding.btnSave.setOnClickListener {
            if(validationPasses()){
                if(!edit){
                    schedule.id = maxScheduleId
                    schedule.checked = true
                }
                schedule.title = binding.etTitle.text.toString()
                schedule.hour = binding.timePickerStartHour.value
                schedule.min = binding.timePickerStartMin.value

                val time = getNextNotification()

                if(time != 0L){
                    val intent = Intent(context, NotificationBroadcast::class.java)
                    intent.putExtra("key", schedule.id)
                    val pi = PendingIntent.getBroadcast(
                        context,
                        schedule.id,
                        intent,
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
                binding.tvMonday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.colorPrimary)
                schedule.monday = false
            }else{
                binding.tvMonday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.greyish)
                schedule.monday = true
            }
        }
        binding.tvTuesday.setOnClickListener {
            if(schedule.tuesday){
                binding.tvTuesday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.colorPrimary)
                schedule.tuesday = false
            }else{
                binding.tvTuesday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.greyish)
                schedule.tuesday = true
            }
        }
        binding.tvWednesday.setOnClickListener {
            if(schedule.wednesday){
                binding.tvWednesday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.colorPrimary)
                schedule.wednesday = false
            }else{
                binding.tvWednesday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.greyish)
                schedule.wednesday = true
            }
        }
        binding.tvThursday.setOnClickListener {
            if(schedule.thursday){
                binding.tvThursday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.colorPrimary)
                schedule.thursday = false
            }else{
                binding.tvThursday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.greyish)
                schedule.thursday = true
            }
        }
        binding.tvFriday.setOnClickListener {
            if(schedule.friday){
                binding.tvFriday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.colorPrimary)
                schedule.friday = false
            }else{
                binding.tvFriday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.greyish)
                schedule.friday = true
            }
        }
        binding.tvSaturday.setOnClickListener {
            if(schedule.saturday){
                binding.tvSaturday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.colorPrimary)
                schedule.saturday = false
            }else{
                binding.tvSaturday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.greyish)
                schedule.saturday = true
            }
        }
        binding.tvSunday.setOnClickListener {
            if(schedule.sunday){
                binding.tvSunday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.colorPrimary)
                schedule.sunday = false
            }else{
                binding.tvSunday.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.greyish)
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