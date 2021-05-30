package com.patates.gamercontrol.ui.oyunaBasla

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.patates.gamercontrol.R
import com.patates.gamercontrol.ui.yardimciSiniflar.*
import kotlinx.android.synthetic.main.fragment_oyuna_basla.*


class OyunaBaslaFragment : Fragment() {

    var gameId:Int=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_oyuna_basla, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.let {
        gameId=OyunaBaslaFragmentArgs.fromBundle(it).gameId
        }
        context?.let {
            var game=Db.getGame(gameId,it)
            game?.let {
                textGameName.text=it.gameName
                imageViewGameImage.setImageResource(it.gameImageId)
            }
            var alarm=0
            Sp.get<Int>("Alarm",it)?.let {
                alarm=it
            }
            if(alarm!=0){
                if (alarm==gameId){
                    editTextTime.visibility=View.INVISIBLE
                    btnOyunaBasla.text="alarmı kapat"
                    btnOyunaBasla.setOnClickListener {
                        context?.let {
                            var i=Intent(it.applicationContext,MyBrodcastReceiver::class.java)
                            var pi=PendingIntent.getBroadcast(it.applicationContext,111,i,0)
                            var i2= Intent(it.applicationContext,MyBrodcastReceiverBildirim::class.java)
                            var pi2= PendingIntent.getBroadcast(it.applicationContext,112,i2,0)
                            var am=it.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                            am.cancel(pi)
                            am.cancel(pi2)
                            Sp.remove("Alarm",it)
                            Db.updateStopGame(it)
                            activity?.let {a->
                                JavaAraclari.klavyeKapat(it,a)
                                a.onBackPressed()
                            }
                        }
                    }
                }else{
                    editTextTime.visibility=View.INVISIBLE
                    btnOyunaBasla.text="kurulu bir alarm var"
                }

            }else{
                btnOyunaBasla.setOnClickListener {
                    if (editTextTime.text.toString().toInt() > 5) {
                        context?.let {
                            var i = Intent(it.applicationContext, MyBrodcastReceiver::class.java)
                            var pi = PendingIntent.getBroadcast(it.applicationContext, 111, i, 0)
                            var i2 = Intent(
                                it.applicationContext,
                                MyBrodcastReceiverBildirim::class.java
                            )
                            var pi2 = PendingIntent.getBroadcast(it.applicationContext, 112, i2, 0)
                            var am = it.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                            try {
                                var mun = editTextTime.text.toString().toInt()
                                var sec: Long = mun.toLong() * 60
                                am.set(
                                    AlarmManager.RTC_WAKEUP,
                                    System.currentTimeMillis() + (sec * 1000),
                                    pi
                                )
                                am.set(
                                    AlarmManager.RTC_WAKEUP,
                                    System.currentTimeMillis() + ((sec - 300) * 1000),
                                    pi2
                                )
                                Toast.makeText(context, "Alarm ${sec}", Toast.LENGTH_LONG).show()
                                Sp.add("Alarm", gameId, it)
                                Db.addStartGame(gameId, it)
                            } catch (e: Exception) {
                                println(e.message)
                            }

                        }
                        activity?.let {
                            JavaAraclari.klavyeKapat(context, it)
                            it.onBackPressed()
                        }
                    }else{
                        Toast.makeText(context, "5 dakikadan çok olmalı", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btnIstatistikDetay.setOnClickListener {
            val action=OyunaBaslaFragmentDirections.actionOyunaBaslaFragmentToFragmentid(gameId)
            Navigation.findNavController(it).navigate(action)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}