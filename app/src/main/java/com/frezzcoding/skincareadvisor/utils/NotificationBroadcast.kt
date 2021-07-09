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
        val list : ArrayList<Schedule> = gson.fromJson<Any>(json, type) as ArrayList<Schedule>

        for (i in list) {
            if (i.id == key) {
                schedule = i
            }
        }
        val builder: NotificationCompat.Builder
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
            val notificationManager = NotificationManagerCompat.from(context)
            createNewNotification(context)
            notificationManager.notify(200, builder.build())
        }
    }

    private fun createNewNotification(context: Context) {
        val time = getNextNotification()
        if (time == 0L) {
            //don't notify
        } else {
            val intent = Intent(context, NotificationBroadcast::class.java)
            intent.putExtra(INTENT_NOTIFICATION_KEY, key)
            val pi =
                PendingIntent.getBroadcast(context, key, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pi)
        }
    }



    //this can cause errors if notification is set for 23:59 but is sent out with a potential delay. This would make the 'today' day different
    private fun getNextNotification(): Long {
        //get current day
        val calendar = Calendar.getInstance()
        val currentDay = calendar.time.toString().substring(0,3)

        return getNextNotificationDay(schedule, currentDay)
    }


}