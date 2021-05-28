package com.saye.gamercontrolcopy.ui.OyunEkle

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.patates.gamercontrol.R
import kotlinx.android.synthetic.main.fragment_oyun_ekle.*
import java.lang.Exception


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
            context?.let {
                val db=it.openOrCreateDatabase("Games", Context.MODE_PRIVATE,null)
                try {
                    val contentValues = ContentValues()
                    contentValues.put("GameName",txtOyunAdi.text.toString())
                    contentValues.put("GameImageId",R.drawable.ic_menu_gallery)
                    db.insert("Games",null,contentValues)
                    db.close()
                }catch (e:Exception){

                }
            }

            activity?.let {
                it.onBackPressed()
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

}