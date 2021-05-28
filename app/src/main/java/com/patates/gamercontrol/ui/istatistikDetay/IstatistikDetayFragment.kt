package com.patates.gamercontrol.ui.istatistikDetay

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.patates.gamercontrol.R
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
    }

}