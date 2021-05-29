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
import kotlinx.android.synthetic.main.fragment_istatistik_detay.*


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
            istatistikDetaytxt.text=gameId.toString()
        }
        /*context?.let { c->
            button.setOnClickListener {
                alert(it,c)
            }
        }*/
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