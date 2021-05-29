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
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import com.patates.gamercontrol.R
import com.patates.gamercontrol.ui.kutuphane.KutuphaneFragmentDirections
import com.patates.gamercontrol.ui.yardimciSiniflar.Db
import com.patates.gamercontrol.ui.yardimciSiniflar.MyBrodcastReceiver
import com.patates.gamercontrol.ui.yardimciSiniflar.Sp
import kotlinx.android.synthetic.main.activity_library.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.bell.*
import kotlinx.android.synthetic.main.fragment_oyuna_basla.*
import java.util.*


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
                textViewGameName.text=it.gameName
            }
            /*var sharedPreferences=it.getSharedPreferences("com.patates.gamercontrol",Context.MODE_PRIVATE)
            var alarm=sharedPreferences.getInt("Alarm",0)*/
            var alarm=0
            Sp.get<Int>("Alarm",it)?.let {
                alarm=it
            }
            if(alarm!=0){
                if (alarm==gameId){
                    btnOyunaBasla.text="alarmı kapat"
                    btnOyunaBasla.setOnClickListener {
                        context?.let {
                            var i=Intent(it.applicationContext,MyBrodcastReceiver::class.java)
                            var pi=PendingIntent.getBroadcast(it.applicationContext,111,i,0)
                            var am=it.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                            am.cancel(pi)
                           /* var sharedPreferences=it.getSharedPreferences("com.patates.gamercontrol",Context.MODE_PRIVATE)
                            sharedPreferences.edit().remove("Alarm").apply()*/
                            Sp.remove("Alarm",it)
                            Db.updateStopGame(it)
                            activity?.let {
                                it.onBackPressed()
                            }
                        }
                    }
                }else{
                    btnOyunaBasla.text="kurulu bir alarm var"
                }

            }else{
                btnOyunaBasla.setOnClickListener {
                    context?.let {
                        var i= Intent(it.applicationContext,MyBrodcastReceiver::class.java)
                        var pi= PendingIntent.getBroadcast(it.applicationContext,111,i,0)
                        var am=it.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                        try {
                            var mun=editTextTime.text.toString().toInt()
                            //var sec:Int=mun*60
                            var sec=5
                            am.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(sec*1000),pi)

                            Toast.makeText(context,"Alarm ${sec}", Toast.LENGTH_LONG).show()
                           /* var sharedPreferences=it.getSharedPreferences("com.patates.gamercontrol",Context.MODE_PRIVATE)
                            sharedPreferences.edit().putInt("Alarm",gameId).apply()*/
                            Sp.add("Alarm",gameId,it)
                            Db.addStartGame(gameId,it)
                        }catch (e:Exception){
                            println(e.message)
                        }

                    }
                    activity?.let {
                        it.onBackPressed()
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