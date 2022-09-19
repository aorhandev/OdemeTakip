package com.aorhan.odemetakip.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aorhan.odemetakip.R

class OdemeTipiHolder(itemView: View, itemClick : (position : Int)-> Unit) :RecyclerView.ViewHolder(itemView){

    var tvOdemeTipiAdi : TextView
    var tvPeriyod : TextView
    var tvPeriyodSuresi : TextView

    init {
        tvOdemeTipiAdi =  itemView.findViewById(R.id.tvOdemeTipiAdi)
        tvPeriyod =  itemView.findViewById(R.id.tvPeriyod)
        tvPeriyodSuresi =  itemView.findViewById(R.id.tvPeriyodSuresi)

        itemView.setOnClickListener{
                view ->
            itemClick(adapterPosition)

        }
    }
}