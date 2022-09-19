package com.aorhan.odemetakip.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aorhan.odemetakip.holders.OdemeTipiHolder
import com.aorhan.odemetakip.R
import com.aorhan.odemetakip.entities.OdemeTipi

class OdemeTipiAdapter (val context: Context, var liste : ArrayList<OdemeTipi>, itemClick : (position : Int)-> Unit) : RecyclerView.Adapter<OdemeTipiHolder>() {

    val itemClick = itemClick
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OdemeTipiHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.odeme_tipi_liste, parent, false)
        return  OdemeTipiHolder(v,itemClick)
    }

    override fun onBindViewHolder(holder: OdemeTipiHolder, position: Int) {
        val odemeTipi = liste.get(position)
        holder.tvOdemeTipiAdi.text = odemeTipi.OdemeTipiAdi
        holder.tvPeriyod.text = odemeTipi.Periyod
        // holder.tvPeriyodSuresi.text = odemeTipi.PeriyodSuresi
    }

    override fun getItemCount(): Int {
        return liste.size
    }
}