package com.patates.gamercontrol.ui.yardimciSiniflar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.patates.gamercontrol.R

class IstatistikListReyclerAdaptor(val istatistikList:ArrayList<Istatistik>):RecyclerView.Adapter<IstatistikListReyclerAdaptor.IstatistikVH>() {
    class IstatistikVH(itemView: View) : RecyclerView.ViewHolder(itemView){


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IstatistikVH {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.istatistik_list_recycler_row,parent,false)
        return IstatistikVH(itemView)

    }


    override fun onBindViewHolder(holder: IstatistikVH, position: Int) {


    }


    override fun getItemCount(): Int {

        return istatistikList.size
    }
}