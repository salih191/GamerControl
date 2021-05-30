package com.patates.gamercontrol.ui.istatistikDetay

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.patates.gamercontrol.R
import com.patates.gamercontrol.ui.yardimciSiniflar.Db
import com.patates.gamercontrol.ui.yardimciSiniflar.Zaman.gunlukOrtalamaOynamaSaati
import com.patates.gamercontrol.ui.yardimciSiniflar.Zaman.ortalamaCikisSaati
import com.patates.gamercontrol.ui.yardimciSiniflar.Zaman.ortalamaOyunaGirisSaati
import com.patates.gamercontrol.ui.yardimciSiniflar.Zaman.oyunOynadigiGunSayisi
import com.patates.gamercontrol.ui.yardimciSiniflar.Zaman.toplamOynamaSuresi
import kotlinx.android.synthetic.main.fragment_istatistik_detay.*
import java.util.*


class IstatistikDetayFragment : Fragment() {
    var gameId:Int=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_istatistik_detay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            gameId=IstatistikDetayFragmentArgs.fromBundle(it).gameId
        }
        context?.let { c->
            var data=Db.getByGameIDTimeList(gameId,c)
            var game=Db.getGame(gameId,c)
            game?.let {
                textGameName.text=it.gameName
                imageViewGameImage.setImageResource(it.gameImageId)
                var sure=toplamOynamaSuresi(data)
                textViewToplamOynamaSuresi.text="${sure.saat}.${sure.dakika} saat"
                sure=gunlukOrtalamaOynamaSaati(data)
                textViewGunlukOrtalamaOynamaSaati.text="${sure.saat}.${sure.dakika} saat"
                textViewOyunOynadigiGunSayisi.text= oyunOynadigiGunSayisi(data).toString()+"gün"
            }
            btnOyunuSil.setOnClickListener {
                alert(it,c)
            }
        }
    }
    fun alert(view: View,context: Context){

        val alertMassage = AlertDialog.Builder(context)
        alertMassage.setTitle("Uyarı")
        alertMassage.setMessage("Silme İşlemine devam etmek isyor musun")

        alertMassage.setPositiveButton("Onayla",
            DialogInterface.OnClickListener { dialogInterface, i ->
                Db.removeGame(gameId,context)
                var action=IstatistikDetayFragmentDirections.actionFragmentidToNavKutuphane()
                Navigation.findNavController(view).navigate(action)
                Toast.makeText(context,"silindi", Toast.LENGTH_LONG).show()
            })
        alertMassage.setNegativeButton("İptal", DialogInterface.OnClickListener { dialogInterface, i ->
            Toast.makeText(context,"İşlem iptal edildi", Toast.LENGTH_LONG).show()
        })
        alertMassage.show()
    }

}