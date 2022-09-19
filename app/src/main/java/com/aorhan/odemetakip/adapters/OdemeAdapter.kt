package com.aorhan.odemetakip.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aorhan.odemetakip.R
import com.aorhan.odemetakip.entities.Odeme
import com.aorhan.odemetakip.entities.OdemeTipi
import com.aorhan.odemetakip.holders.OdemeHolder

class OdemeAdapter (val context: Context, var liste : ArrayList<Odeme>, itemClick : (position : Int)-> Unit) : RecyclerView.Adapter<OdemeHolder>() {

    val itemClick = itemClick
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OdemeHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.odeme_tipi_liste, parent, false)
        return  OdemeHolder(v,itemClick)
    }

    override fun onBindViewHolder(holder: OdemeHolder, position: Int) {
        val odeme = liste.get(position)
        holder.tvTarih.text = odeme.OdemeTarihi
        holder.tvTutar.text = odeme.Tutar
    }

    override fun getItemCount(): Int {
        return liste.size
    }
}