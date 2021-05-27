package com.patates.gamercontrol.ui.yardimciSiniflar

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.patates.gamercontrol.ui.alarm.AlarmActivity

class MyBrodcastReceiver:BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        var i=Intent(context,AlarmActivity::class.java)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context?.startActivity(i)
    }
}