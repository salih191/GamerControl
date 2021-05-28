package com.patates.gamercontrol.ui.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.patates.gamercontrol.R
import com.patates.gamercontrol.ui.yardimciSiniflar.Db
import com.patates.gamercontrol.ui.yardimciSiniflar.MyBrodcastReceiver
import com.patates.gamercontrol.ui.yardimciSiniflar.Sp
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
            /*var sharedPreferences=getSharedPreferences("com.patates.gamercontrol",Context.MODE_PRIVATE)
            val gameId:Int=sharedPreferences.getInt("Alarm",0)*/
            val gameId=Sp.get<Int>("Alarm",this)
            Db.updateStopGame(this)
            //sharedPreferences.edit().remove("Alarm").apply()
            Sp.remove("Alarm",this)
            Sp.remove("timeId",this)
            finish()
            System.exit(0)
        }
        btnAlarmErtele.setOnClickListener {
            mp.stop()
            finish()
        }
    }

    override fun onBackPressed() {

    }

}