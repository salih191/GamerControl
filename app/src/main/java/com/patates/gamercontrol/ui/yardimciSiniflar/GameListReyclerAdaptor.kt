package com.patates.gamercontrol

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.patates.gamercontrol.ui.kutuphane.KutuphaneFragmentDirections
import com.patates.gamercontrol.ui.yardimciSiniflar.Game
import kotlinx.android.synthetic.main.game_list_recycler_row.view.*


class GameListReyclerAdaptor(val gameList : ArrayList<Game> ) : RecyclerView.Adapter<GameListReyclerAdaptor.GamesVH>() {

    class GamesVH(itemView: View) : RecyclerView.ViewHolder(itemView){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesVH {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.game_list_recycler_row,parent,false)
        return GamesVH(itemView)

    }


    override fun onBindViewHolder(holder: GamesVH, position: Int) {

        holder.itemView.recyclerViewTextView.text = gameList.get(position).gameName
        holder.itemView.recyclerViewImageView.setImageResource(gameList[position].gameImageId)
        holder.itemView.setOnClickListener{
            var action=KutuphaneFragmentDirections.actionNavKutuphaneToOyunaBaslaFragment(gameList[position].gameId)
            Navigation.findNavController(it).navigate(action)
        }
    }


    override fun getItemCount(): Int {

        return gameList.size
    }

}