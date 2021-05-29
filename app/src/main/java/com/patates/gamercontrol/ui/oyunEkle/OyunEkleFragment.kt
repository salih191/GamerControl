package com.saye.gamercontrolcopy.ui.OyunEkle

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.patates.gamercontrol.R
import com.patates.gamercontrol.ui.yardimciSiniflar.Db
import com.patates.gamercontrol.ui.yardimciSiniflar.Game
import kotlinx.android.synthetic.main.fragment_oyun_ekle.*


class OyunEkleFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_oyun_ekle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnOyunEkle.setOnClickListener {
            context?.let {c->
                val game=Game(0,txtOyunAdi.text.toString(),R.drawable.ic_menu_gallery)
                Db.addGame(game,c)

                activity?.let {
                    it.onBackPressed()
                }
            }


        }
        super.onViewCreated(view, savedInstanceState)
    }

}