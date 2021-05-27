package com.patates.gamercontrol.ui.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.patates.gamercontrol.R
import com.patates.gamercontrol.ui.yardimciSiniflar.MyBrodcastReceiver
import kotlinx.android.synthetic.main.activity_alarm.*


class AlarmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
        var alert:Uri=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        var mp= MediaPlayer.create(applicationContext,alert)
        mp.setVolume(0.5F, 0.1F)
        mp.isLooping=true
        mp.start()
        btnAlarmKapat.setOnClickListener{
            mp.stop()
            finish()
        }
        btnAlarmErtele.setOnClickListener {
            mp.stop()
            var i= Intent(applicationContext,MyBrodcastReceiver::class.java)
            var pi= PendingIntent.getBroadcast(applicationContext,111,i,0)
            var am=getSystemService(Context.ALARM_SERVICE) as AlarmManager
            am.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(5*60*1000),pi)
            finish()
        }
    }
}