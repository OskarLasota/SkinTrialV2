package com.frezzcoding.skincareadvisor.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.frezzcoding.skincareadvisor.data.Schedule
import com.frezzcoding.skincareadvisor.data.User


@Database(entities = [User::class, Schedule::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun scheduleDao() : ScheduleDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "user_database"
                    ).build()
                }
                return instance
            }
        }
    }
}