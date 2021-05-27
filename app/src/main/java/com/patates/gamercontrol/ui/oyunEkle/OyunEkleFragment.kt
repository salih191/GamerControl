package com.saye.gamercontrolcopy.ui.OyunEkle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.patates.gamercontrol.R
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
            activity.let {
                it!!.onBackPressed()
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

}