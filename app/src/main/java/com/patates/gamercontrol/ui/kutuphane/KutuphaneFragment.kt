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
import com.patates.gamercontrol.ui.yardimciSiniflar.Game
import kotlinx.android.synthetic.main.activity_library.*

class KutuphaneFragment : Fragment() {

    var games= ArrayList<Game>()
private lateinit var listeAdapter: GameListReyclerAdaptor
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bildirimIkonu.setOnClickListener {
            println("asda")
        }
       context?.let {
           var sharedPreferences=it.getSharedPreferences("com.patates.gamercontrol",Context.MODE_PRIVATE)
           var alarm=sharedPreferences.getInt("Alarm",0)
           if(alarm!=0){
               bildirimIkonu.visibility=View.VISIBLE
               bildirimIkonu.setOnClickListener {
                   var action=KutuphaneFragmentDirections.actionNavKutuphaneToOyunaBaslaFragment(alarm)
                   Navigation.findNavController(it).navigate(action)
               }
           }
       }
        fab.setOnClickListener {

            val action=KutuphaneFragmentDirections.actionNavKutuphaneToOyunEkleFragment2()
            Navigation.findNavController(it).navigate(action)
        }

        //var game:Game=Game(1,"valorant",R.drawable.ic_launcher_foreground)
        /*games.clear()
        games.add(game)*/
        sqlVeriCekme()
        val layoutManager = LinearLayoutManager(this.context)

        recyclerView.layoutManager = layoutManager
        listeAdapter = GameListReyclerAdaptor(games)
        recyclerView.adapter = listeAdapter
        listeAdapter.notifyDataSetChanged()
        super.onViewCreated(view, savedInstanceState)
    }
    fun sqlVeriCekme(){
        context?.let {
            try {
                val db=it.openOrCreateDatabase("Games", Context.MODE_PRIVATE,null)
                val cursor=db.rawQuery("select * from Games",null)
                val gameIdIndex=cursor.getColumnIndex("GameID")
                val gameNameIndex=cursor.getColumnIndex("GameName")
                val gameImageIdIndex=cursor.getColumnIndex("GameImageId")
                games.clear()
                while (cursor.moveToNext()){
                    var id=cursor.getInt(gameIdIndex)
                    var name=cursor.getString(gameNameIndex)
                    var imageId=cursor.getInt(gameImageIdIndex)
                    var game=Game(id,name,imageId)
                    games.add(game)
                }
                db.close()
                listeAdapter.notifyDataSetChanged()//veri değişirse rw nin güncellenmesi
            }catch (e:Exception){
                println(e.message)
            }
        }




    }
}