package com.frezzcoding.skincareadvisor.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.frezzcoding.skincareadvisor.MainActivity
import com.frezzcoding.skincareadvisor.R
import com.frezzcoding.skincareadvisor.data.Schedule
import com.frezzcoding.skincareadvisor.utils.Keys.INTENT_NOTIFICATION_KEY
import com.frezzcoding.skincareadvisor.utils.Keys.NOTIFICATION_BROADCAST_KEY
import com.frezzcoding.skincareadvisor.utils.Keys.SHARED_PREFERENCES_NAME
import com.frezzcoding.skincareadvisor.utils.Keys.SP_SCHEDULES_KEY
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


class NotificationBroadcast : BroadcastReceiver() {

    private var key: Int = 0
    private lateinit var schedule: Schedule

    private val channelId = "notify"

    override fun onReceive(context: Context, intent: Intent) {
        key = intent.getIntExtra(NOTIFICATION_BROADCAST_KEY, 0)

        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

        val gson = Gson()
        val json = sharedPreferences.getString(SP_SCHEDULES_KEY, null)
        val type: Type = object : TypeToken<ArrayList<Schedule>>() {}.type
        var list = gson.fromJson<Any>(json, type) as ArrayList<Schedule>

        for (i in list) {
            if (i.id == key) {
                schedule = i
            }
        }
        var builder: NotificationCompat.Builder
        if(this::schedule.isInitialized) {
            builder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.logo_small)
                .setContentTitle(context.getString(R.string.notificaiton_title))
                .setContentText("${context.getString(R.string.notification_description)} " + schedule.title)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(
                    PendingIntent.getActivity(
                        context,
                        0,
                        Intent(context, MainActivity::class.java),
                        0
                    )
                )
            var notificationManager = NotificationManagerCompat.from(context)
            createNewNotification(context)
            notificationManager.notify(200, builder.build())
        }
    }

    private fun createNewNotification(context: Context) {
        var time = getNextNotification()
        if (time == 0L) {
            //don't notify
        } else {
            var inte = Intent(context, NotificationBroadcast::class.java)
            inte.putExtra(INTENT_NOTIFICATION_KEY, key)
            val pi =
                PendingIntent.getBroadcast(context, key, inte, PendingIntent.FLAG_UPDATE_CURRENT)
            val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pi)
        }
    }

    private fun getNextNotification(): Long {
        val calendar = Calendar.getInstance()
        if (calendar.time.toString().substring(0, 3) == "Mon") {
            val calendar1 = Calendar.getInstance()
            for (i in 0..6) {
                calendar1.add(Calendar.DATE, 1)
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
            }
        }
        if (calendar.time.toString().substring(0, 3) == "Tue") {
            val calendar1 = Calendar.getInstance()
            for (i in 0..6) {
                calendar1.add(Calendar.DATE, 1)
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
            }
        }
        if (calendar.time.toString().substring(0, 3) == "Wed") {
            val calendar1 = Calendar.getInstance()
            for (i in 0..6) {
                calendar1.add(Calendar.DATE, 1)
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
            }
        }
        if (calendar.time.toString().substring(0, 3) == "Thu") {
            val calendar1 = Calendar.getInstance()
            for (i in 0..6) {
                calendar1.add(Calendar.DATE, 1)
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
            }
        }
        if (calendar.time.toString().substring(0, 3) == "Fri") {
            val calendar1 = Calendar.getInstance()
            for (i in 0..6) {
                calendar1.add(Calendar.DATE, 1)
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
            }
        }
        if (calendar.time.toString().substring(0, 3) == "Sat") {
            val calendar1 = Calendar.getInstance()
            for (i in 0..6) {
                calendar1.add(Calendar.DATE, 1)
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
            }
        }
        if (calendar.time.toString().substring(0, 3) == "Sun") {
            val calendar1 = Calendar.getInstance()
            for (i in 0..6) {
                calendar1.add(Calendar.DATE, 1)
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
            }
        }
        return 0
    }


}