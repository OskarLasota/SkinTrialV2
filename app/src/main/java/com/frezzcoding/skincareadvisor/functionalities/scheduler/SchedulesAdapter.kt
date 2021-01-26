package com.frezzcoding.skincareadvisor.functionalities.scheduler

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.frezzcoding.skincareadvisor.R
import com.frezzcoding.skincareadvisor.data.Schedule
import com.frezzcoding.skincareadvisor.databinding.CardSchedulesBinding
import com.frezzcoding.skincareadvisor.utils.NotificationBroadcast
import maes.tech.intentanim.CustomIntent
import java.util.*


class SchedulesAdapter(
    private val _data: List<Schedule>,
    private val viewModel: ScheduleCachingViewModel,
    private var listener: OnItemClickListener,
    private var ctx: Context
) : RecyclerView.Adapter<SchedulesAdapter.ViewHolder>() {

    private lateinit var binding: CardSchedulesBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater: LayoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.card_schedules, parent, false)

        return ViewHolder(binding, viewModel, ctx)
    }

    override fun getItemCount(): Int {
        return _data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val schedule: Schedule = _data[position]
        holder.bind(schedule, listener)
    }

    interface OnItemClickListener {
        fun onItemClick(schedule: Schedule)
        //fun onLongPressClickListener(product : Product)
    }

    class ViewHolder(
        private val binding: CardSchedulesBinding,
        private val viewModel: ScheduleCachingViewModel,
        private val ctx: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var schedule: Schedule
        private lateinit var _listener: OnItemClickListener

        init {
            binding.cardview.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.editScheduleFragment)
                CustomIntent.customType(it.context, "fadein-to-fadeout")
            }
            itemView.setOnClickListener {
                _listener.onItemClick(schedule)
            }
        }

        fun bind(schedule: Schedule, listener: OnItemClickListener) {
            _listener = listener
            binding.schedule = schedule
            binding.tvTime.text =
                (if (schedule.hour < 10) "0" else "") + schedule.hour.toString() + " : " + (if (schedule.min < 10) "0" else "") + schedule.min.toString()
            this.schedule = schedule
            binding.switch1.setOnClickListener {
                if (binding.switch1.isChecked) {
                    var time = getNextNotification(schedule)
                    if (time != 0L) {
                        var inte = Intent(ctx, NotificationBroadcast::class.java)
                        inte.putExtra("key", schedule.id)
                        val pi = PendingIntent.getBroadcast(
                            ctx,
                            schedule.id,
                            inte,
                            PendingIntent.FLAG_UPDATE_CURRENT
                        )
                        val am =
                            ctx.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                        am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pi)
                    }

                    schedule.checked = true
                    viewModel.updateSchedule(schedule)
                } else {
                    var intent = Intent(ctx, NotificationBroadcast::class.java)
                    var pendingIntent = PendingIntent.getBroadcast(
                        ctx,
                        schedule.id,
                        intent,
                        PendingIntent.FLAG_CANCEL_CURRENT
                    )
                    var alarmManager = ctx.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    alarmManager.cancel(pendingIntent)
                    schedule.checked = false
                    viewModel.updateSchedule(schedule)
                }
            }
        }


        private fun getNextNotification(schedule: Schedule): Long {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, schedule.hour)
            calendar.set(Calendar.MINUTE, schedule.min)

            if (calendar.time.toString().substring(0, 3) == "Mon") {
                val current = Calendar.getInstance()
                val calendar1 = Calendar.getInstance()
                calendar1.set(Calendar.HOUR_OF_DAY, schedule.hour)
                calendar1.set(Calendar.MINUTE, schedule.min)
                for (i in 0..7) {
                    if (current.timeInMillis > calendar1.timeInMillis) {
                        calendar1.add(Calendar.DATE, 1)
                        continue
                    }
                    when (i) {
                        0 -> if (schedule.monday) {
                            return calendar1.timeInMillis
                        }
                        1 -> if (schedule.tuesday) {
                            return calendar1.timeInMillis
                        }
                        2 -> if (schedule.wednesday) {
                            return calendar1.timeInMillis
                        }
                        3 -> if (schedule.thursday) {
                            return calendar1.timeInMillis
                        }
                        4 -> if (schedule.friday) {
                            return calendar1.timeInMillis
                        }
                        5 -> if (schedule.saturday) {
                            return calendar1.timeInMillis
                        }
                        6 -> if (schedule.sunday) {
                            return calendar1.timeInMillis
                        }
                    }
                    calendar1.add(Calendar.DATE, 1)
                }
            }
            if (calendar.time.toString().substring(0, 3) == "Tue") {
                val current = Calendar.getInstance()
                val calendar1 = Calendar.getInstance()
                calendar1.set(Calendar.HOUR_OF_DAY, schedule.hour)
                calendar1.set(Calendar.MINUTE, schedule.min)
                for (i in 0..7) {
                    if (current.timeInMillis > calendar1.timeInMillis) {
                        calendar1.add(Calendar.DATE, 1)
                        continue
                    }
                    when (i) {
                        0 -> if (schedule.tuesday) {
                            return calendar1.timeInMillis
                        }
                        1 -> if (schedule.wednesday) {
                            return calendar1.timeInMillis
                        }
                        2 -> if (schedule.thursday) {
                            return calendar1.timeInMillis
                        }
                        3 -> if (schedule.friday) {
                            return calendar1.timeInMillis
                        }
                        4 -> if (schedule.saturday) {
                            return calendar1.timeInMillis
                        }
                        5 -> if (schedule.sunday) {
                            return calendar1.timeInMillis
                        }
                        6 -> if (schedule.monday) {
                            return calendar1.timeInMillis
                        }
                    }
                    calendar1.add(Calendar.DATE, 1)
                }
            }
            if (calendar.time.toString().substring(0, 3) == "Wed") {
                val current = Calendar.getInstance()
                val calendar1 = Calendar.getInstance()
                calendar1.set(Calendar.HOUR_OF_DAY, schedule.hour)
                calendar1.set(Calendar.MINUTE, schedule.min)
                for (i in 0..7) {
                    if (current.timeInMillis > calendar1.timeInMillis) {
                        calendar1.add(Calendar.DATE, 1)
                        continue
                    }
                    when (i) {
                        0 -> if (schedule.wednesday) {
                            return calendar1.timeInMillis
                        }
                        1 -> if (schedule.thursday) {
                            return calendar1.timeInMillis
                        }
                        2 -> if (schedule.friday) {
                            return calendar1.timeInMillis
                        }
                        3 -> if (schedule.saturday) {
                            return calendar1.timeInMillis
                        }
                        4 -> if (schedule.sunday) {
                            return calendar1.timeInMillis
                        }
                        5 -> if (schedule.monday) {
                            return calendar1.timeInMillis
                        }
                        6 -> if (schedule.tuesday) {
                            return calendar1.timeInMillis
                        }
                    }
                    calendar1.add(Calendar.DATE, 1)
                }
            }
            if (calendar.time.toString().substring(0, 3) == "Thu") {
                val current = Calendar.getInstance()
                val calendar1 = Calendar.getInstance()
                calendar1.set(Calendar.HOUR_OF_DAY, schedule.hour)
                calendar1.set(Calendar.MINUTE, schedule.min)
                for (i in 0..7) {
                    if (current.timeInMillis > calendar1.timeInMillis) {
                        calendar1.add(Calendar.DATE, 1)
                        continue
                    }
                    when (i) {
                        0 -> if (schedule.thursday) {
                            return calendar1.timeInMillis
                        }
                        1 -> if (schedule.friday) {
                            return calendar1.timeInMillis
                        }
                        2 -> if (schedule.saturday) {
                            return calendar1.timeInMillis
                        }
                        3 -> if (schedule.sunday) {
                            return calendar1.timeInMillis
                        }
                        4 -> if (schedule.monday) {
                            return calendar1.timeInMillis
                        }
                        5 -> if (schedule.tuesday) {
                            return calendar1.timeInMillis
                        }
                        6 -> if (schedule.wednesday) {
                            return calendar1.timeInMillis
                        }
                    }
                    calendar1.add(Calendar.DATE, 1)
                }
            }
            if (calendar.time.toString().substring(0, 3) == "Fri") {
                val current = Calendar.getInstance()
                val calendar1 = Calendar.getInstance()
                calendar1.set(Calendar.HOUR_OF_DAY, schedule.hour)
                calendar1.set(Calendar.MINUTE, schedule.min)
                for (i in 0..7) {
                    if (current.timeInMillis > calendar1.timeInMillis) {
                        calendar1.add(Calendar.DATE, 1)
                        continue
                    }
                    when (i) {
                        0 -> if (schedule.friday) {
                            return calendar1.timeInMillis
                        }
                        1 -> if (schedule.saturday) {
                            return calendar1.timeInMillis
                        }
                        2 -> if (schedule.sunday) {
                            return calendar1.timeInMillis
                        }
                        3 -> if (schedule.monday) {
                            return calendar1.timeInMillis
                        }
                        4 -> if (schedule.tuesday) {
                            return calendar1.timeInMillis
                        }
                        5 -> if (schedule.wednesday) {
                            return calendar1.timeInMillis
                        }
                        6 -> if (schedule.thursday) {
                            return calendar1.timeInMillis
                        }
                    }
                    calendar1.add(Calendar.DATE, 1)
                }
            }
            if (calendar.time.toString().substring(0, 3) == "Sat") {
                val current = Calendar.getInstance()
                val calendar1 = Calendar.getInstance()
                calendar1.set(Calendar.HOUR_OF_DAY, schedule.hour)
                calendar1.set(Calendar.MINUTE, schedule.min)
                for (i in 0..7) {
                    if (current.timeInMillis > calendar1.timeInMillis) {
                        calendar1.add(Calendar.DATE, 1)
                        continue
                    }
                    when (i) {
                        0 -> if (schedule.saturday) {
                            return calendar1.timeInMillis
                        }
                        1 -> if (schedule.sunday) {
                            return calendar1.timeInMillis
                        }
                        2 -> if (schedule.monday) {
                            return calendar1.timeInMillis
                        }
                        3 -> if (schedule.tuesday) {
                            return calendar1.timeInMillis
                        }
                        4 -> if (schedule.wednesday) {
                            return calendar1.timeInMillis
                        }
                        5 -> if (schedule.thursday) {
                            return calendar1.timeInMillis
                        }
                        6 -> if (schedule.friday) {
                            return calendar1.timeInMillis
                        }
                    }
                    calendar1.add(Calendar.DATE, 1)
                }
            }
            if (calendar.time.toString().substring(0, 3) == "Sun") {
                val current = Calendar.getInstance()
                val calendar1 = Calendar.getInstance()
                calendar1.set(Calendar.HOUR_OF_DAY, schedule.hour)
                calendar1.set(Calendar.MINUTE, schedule.min)
                for (i in 0..7) {
                    if (current.timeInMillis > calendar1.timeInMillis) {
                        calendar1.add(Calendar.DATE, 1)
                        continue
                    }
                    when (i) {
                        0 -> if (schedule.sunday) {
                            return calendar1.timeInMillis
                        }
                        1 -> if (schedule.monday) {
                            return calendar1.timeInMillis
                        }
                        2 -> if (schedule.tuesday) {
                            return calendar1.timeInMillis
                        }
                        3 -> if (schedule.wednesday) {
                            return calendar1.timeInMillis
                        }
                        4 -> if (schedule.thursday) {
                            return calendar1.timeInMillis
                        }
                        5 -> if (schedule.friday) {
                            return calendar1.timeInMillis
                        }
                        6 -> if (schedule.saturday) {
                            return calendar1.timeInMillis
                        }
                    }
                    calendar1.add(Calendar.DATE, 1)
                }
            }
            return 0
        }

    }
}