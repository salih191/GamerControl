package com.patates.gamercontrol.ui.istatistik

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.patates.gamercontrol.R
import com.patates.gamercontrol.ui.yardimciSiniflar.Db
import com.patates.gamercontrol.ui.yardimciSiniflar.Zaman
import kotlinx.android.synthetic.main.fragment_istatistik.*
import kotlinx.android.synthetic.main.fragment_istatistik.textViewGunlukOrtalamaOynamaSaati
import kotlinx.android.synthetic.main.fragment_istatistik_detay.*

class IstatistikFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_istatistik,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            var data=Db.getTimeList(it)
            var sure= Zaman.toplamOynamaSuresi(data)
            textViewToplamOynamaSaati.text="${sure.saat}.${sure.dakika} saat"
            sure= Zaman.gunlukOrtalamaOynamaSaati(data)
            textViewGunlukOrtalamaOynamaSaati.text="${sure.saat}.${sure.dakika} saat"
            textViewOynadigiGunSayisi.text= Zaman.oyunOynadigiGunSayisi(data).toString()+"gün"
        }
    }
}