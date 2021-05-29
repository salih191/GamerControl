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
import androidx.core.net.toUri
import com.patates.gamercontrol.R
import com.patates.gamercontrol.ui.yardimciSiniflar.Db
import com.patates.gamercontrol.ui.yardimciSiniflar.MyBrodcastReceiver
import com.patates.gamercontrol.ui.yardimciSiniflar.Sp
import kotlinx.android.synthetic.main.activity_alarm.*
import java.io.File
import java.lang.Exception


class AlarmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
        var ses=Sp.get<String>("sesUri",this)
        var defaultRingtone=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        var alert:Uri=defaultRingtone
        if (ses!="")
        {
            alert=ses.toUri()
        }

        var mp= MediaPlayer.create(applicationContext,alert)

        var sesDuzeyi=Sp.get<Int>("sesDuzeyi",this).toFloat()/100
        try {
            mp.setVolume(sesDuzeyi,sesDuzeyi)
            mp.isLooping=true
            mp.start()
        }catch (e:Exception){
            mp= MediaPlayer.create(applicationContext,defaultRingtone)
            mp.setVolume(sesDuzeyi,sesDuzeyi)
            mp.isLooping=true
            mp.start()
        }
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
        }
        btnAlarmErtele.setOnClickListener {
            mp.stop()
            finish()
        }
    }

    override fun onBackPressed() {

    }

}