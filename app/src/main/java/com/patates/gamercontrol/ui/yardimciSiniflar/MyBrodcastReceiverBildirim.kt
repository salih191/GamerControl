package com.patates.gamercontrol.ui.yardimciSiniflar

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.patates.gamercontrol.ui.alarm.AlarmActivity

class MyBrodcastReceiverBildirim:BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        println("bildirim")
    }
}