package com.patates.gamercontrol.ui.kutuphane

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
import com.patates.gamercontrol.R
import com.patates.gamercontrol.RecyclerAdapter
import com.patates.gamercontrol.ui.yardimciSiniflar.Game
import kotlinx.android.synthetic.main.activity_library.*

class KutuphaneFragment : Fragment() {

    //var games= ArrayList<Game>()
private lateinit var listeAdapter: RecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fab.setOnClickListener {
            val action=KutuphaneFragmentDirections.actionNavKutuphaneToOyunEkleFragment2()
            Navigation.findNavController(it).navigate(action)
        }
       /* var image:Bitmap= BitmapFactory.decodeResource(context?.resources,
            R.drawable.ic_menu_gallery
        )
        var game:Game=Game(1,"valorant",image)

        games.add(game)*/
        val layoutManager = LinearLayoutManager(this.context)

        recyclerView.layoutManager = layoutManager
        var games=ArrayList<String>()
        games.add("valorant")
        listeAdapter = RecyclerAdapter(games)
        recyclerView.adapter = listeAdapter

        super.onViewCreated(view, savedInstanceState)
    }
    fun sqlVeriCekme(){



        /*val db=this.openOrCreateDatabase("Games", Context.MODE_PRIVATE,null)
        val cursor=db.rawQuery("select * from Games",null)
        val gameNameIndex=cursor.getColumnIndex("GameName")
        while (cursor.moveToNext()){
            println("name:${cursor.getString(gameNameIndex)}")
            gameNames.add(cursor.getString(gameNameIndex))
        }
        db.close()*/
        listeAdapter.notifyDataSetChanged()//veri değişirse rw nin güncellenmesi


    }
}