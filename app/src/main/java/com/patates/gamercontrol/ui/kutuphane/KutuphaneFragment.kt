package com.patates.gamercontrol.ui.kutuphane

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.patates.gamercontrol.GameListReyclerAdaptor
import com.patates.gamercontrol.R
import com.patates.gamercontrol.ui.yardimciSiniflar.*
import kotlinx.android.synthetic.main.fragment_library.*
import kotlinx.android.synthetic.main.hava_durumu.*

class KutuphaneFragment : Fragment() {

    var games= ArrayList<Game>()
private lateinit var listeAdapter: GameListReyclerAdaptor
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       context?.let {
           var sehir=Sp.get("sehir",it,"istanbul")
           weatherTask(sehir.toLowerCase(),txtsicaklik,imageViewHava,it,textViewHavaNasil).execute()
           games= Db.getGameList(it)
           if (games.size==0){
               textBos.visibility=View.VISIBLE
           }
           var alarm= Sp.get<Int>("Alarm",it)
           if(alarm!=0){
               bildirimIkonu.visibility=View.VISIBLE
               bildirimIkonu.setOnClickListener {
                   var action=KutuphaneFragmentDirections.actionNavKutuphaneToOyunaBaslaFragment(alarm)
                   Navigation.findNavController(it).navigate(action)
               }
           }else{
               bildirimIkonu.visibility=View.INVISIBLE
           }
       }
        fab.setOnClickListener {
            val action=KutuphaneFragmentDirections.actionNavKutuphaneToOyunEkleFragment2()
            Navigation.findNavController(it).navigate(action)
        }
        val layoutManager = LinearLayoutManager(this.context)

        recyclerView.layoutManager = layoutManager
        listeAdapter = GameListReyclerAdaptor(games)
        recyclerView.adapter = listeAdapter
        listeAdapter.notifyDataSetChanged()
        super.onViewCreated(view, savedInstanceState)
    }

}