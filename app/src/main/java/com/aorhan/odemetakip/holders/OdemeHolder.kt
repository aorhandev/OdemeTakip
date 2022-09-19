package com.aorhan.odemetakip.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aorhan.odemetakip.R

class OdemeHolder(itemView: View, itemClick: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

    var tvTarih: TextView
    var tvTutar: TextView

    init {
        tvTarih = itemView.findViewById(R.id.tvTarih)
        tvTutar = itemView.findViewById(R.id.tvTutar)

        itemView.setOnClickListener {
                view ->
            itemClick(adapterPosition)

        }
    }
}