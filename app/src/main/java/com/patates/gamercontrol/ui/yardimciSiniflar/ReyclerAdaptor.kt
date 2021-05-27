package com.patates.gamercontrol

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.patates.gamercontrol.ui.kutuphane.KutuphaneFragmentDirections
import com.patates.gamercontrol.ui.yardimciSiniflar.Game
import kotlinx.android.synthetic.main.recycler_row.view.*

class RecyclerAdapter(val gameList : ArrayList<String> ) : RecyclerView.Adapter<RecyclerAdapter.GamesVH>() {

    class GamesVH(itemView: View) : RecyclerView.ViewHolder(itemView){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesVH {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return GamesVH(itemView)

    }


    override fun onBindViewHolder(holder: GamesVH, position: Int) {

        holder.itemView.recyclerViewTextView.text = gameList.get(position)
        //holder.itemView.recyclerViewImageView.setImageBitmap(gameList[position].gameImage)

    }


    override fun getItemCount(): Int {

        return gameList.size
    }

}