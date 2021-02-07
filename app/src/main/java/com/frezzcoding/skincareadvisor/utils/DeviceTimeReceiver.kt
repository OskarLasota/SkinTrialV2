package com.frezzcoding.skincareadvisor.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class DeviceTimeReceiver : BroadcastReceiver() {
    override fun onReceive(ctx : Context?, intent : Intent?) {
        when(intent?.action){
            Intent.ACTION_TIMEZONE_CHANGED -> handleTimezoneChange()
            Intent.ACTION_TIME_CHANGED -> handleDeviceTimeChange()
        }
    }

    private fun handleTimezoneChange(){

    }

    private fun handleDeviceTimeChange(){

    }

}