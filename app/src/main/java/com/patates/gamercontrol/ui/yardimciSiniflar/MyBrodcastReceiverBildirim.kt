package com.patates.gamercontrol.ui.yardimciSiniflar

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.patates.gamercontrol.R
import com.patates.gamercontrol.ui.alarm.AlarmActivity

class MyBrodcastReceiverBildirim:BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            sendNotification(it)
        }
    }
    private fun sendNotification(context: Context){
        val builder=NotificationCompat.Builder(context,"sona_5dk_kaldi")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Son 5 dakika")
            .setContentText("Oyun sürenizin dolmasına 5 dk kaldı")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)){
            notify(101, builder.build())
        }
    }
}