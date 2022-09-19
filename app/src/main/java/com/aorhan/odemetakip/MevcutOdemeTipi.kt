package com.aorhan.odemetakip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aorhan.odemetakip.adapters.OdemeAdapter
import com.aorhan.odemetakip.databinding.ActivityMevcutOdemeTipiBinding
import com.aorhan.odemetakip.entities.Odeme
import com.aorhan.odemetakip.entities.OdemeTipi

class MevcutOdemeTipi : AppCompatActivity() {

    lateinit var binding: ActivityMevcutOdemeTipiBinding
    var odemeListesi = ArrayList<Odeme>()
    var odemeIslemleri: OdemeIslemleri
    var odemeTipi: OdemeTipi? = null
    var odemeTipiIslemleri: OdemeTipiIslemleri

    init {
        odemeIslemleri = OdemeIslemleri(this)
        odemeTipiIslemleri = OdemeTipiIslemleri(this)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMevcutOdemeTipiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val odemeTipiId = intent.getIntExtra("odemeTipiId", -1)

        odemeTipi = odemeTipiIslemleri.odemeTipiGetir(odemeTipiId)
        binding.tvMevcutOdemeTipiAdi.setText(odemeTipi!!.OdemeTipiAdi)
        binding.tvMevcutOdemePeriyod.setText(odemeTipi!!.Periyod)
        binding.tvMevcutOdemePeriyodSuresi.setText(odemeTipi!!.PeriyodSuresi.toString())

        odemeListesi = odemeIslemleri.tumOdemeleriGetir()

        val lm = LinearLayoutManager(this)
        lm.orientation = LinearLayoutManager.VERTICAL
        binding.rvMevcutOdemeler.layoutManager = lm
        binding.rvMevcutOdemeler.addItemDecoration(DividerItemDecoration(this,lm.orientation))

        binding.rvMevcutOdemeler.adapter = OdemeAdapter(this, odemeListesi, this::itemOnClick)
    }

    fun btnDuzenle_OnClick(view: View) {
        val intent = Intent(this, YeniOdemeTipiEkle::class.java)
        val odemeTipiId = intent.getIntExtra("odemeTipiId", -1)
        intent.putExtra("odemeTipiId", odemeTipiId)
        resultLauncher.launch(intent)
    }

    fun btnOdemeEkle_OnClick(view: View) {
        /*
        val intent = Intent(this, MevcutOdemeTipi::class.java)
        intent.putExtra("odemeTipiId", odemeTipiListesi.get(position).Id)
        resultLauncher.launch(intent)
         */
    }

    fun itemOnClick(position: Int) {

        /*
        val intent = Intent(this, YeniOdemeTipiEkle::class.java)
        intent.putExtra("odemeId", odemeListesi.get(position).Id)
        resultLauncher.launch(intent)

         */

    }


    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {
        if (it.resultCode == RESULT_OK) {
            odemeListesi.clear()
            odemeListesi.addAll(odemeIslemleri.tumOdemeleriGetir())
            binding.rvMevcutOdemeler.adapter!!.notifyDataSetChanged()
        }
    }


}