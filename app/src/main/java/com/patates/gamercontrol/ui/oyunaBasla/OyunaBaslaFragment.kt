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
import com.patates.gamercontrol.R
import com.patates.gamercontrol.ui.yardimciSiniflar.MyBrodcastReceiver
import kotlinx.android.synthetic.main.fragment_oyuna_basla.*


class OyunaBaslaFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_oyuna_basla, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnOyunaBasla.setOnClickListener {
            context.let {
                var i= Intent(context,MyBrodcastReceiver::class.java)
                var pi= PendingIntent.getBroadcast(context,111,i,0)
                var am=requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
                try {
                   // var mun=(editText.text.toString().toInt()*60)+editText2.text.toString().toInt()
                    //var sec:Int=mun*60
                    var sec=5
                    am.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(sec*1000),pi)
                    Toast.makeText(context,"Alarm $sec", Toast.LENGTH_LONG).show()
                }catch (e:Exception){
                    println(e.message)
                }

            }
        }
    }
}