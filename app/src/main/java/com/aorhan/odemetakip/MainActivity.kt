package com.aorhan.odemetakip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aorhan.odemetakip.adapters.OdemeTipiAdapter
import com.aorhan.odemetakip.databinding.ActivityMainBinding
import com.aorhan.odemetakip.entities.OdemeTipi

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var odemeTipiListesi = ArrayList<OdemeTipi>()
    var odemeTipiIslemleri: OdemeTipiIslemleri

    init {
        odemeTipiIslemleri = OdemeTipiIslemleri(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        odemeTipiListesi = odemeTipiIslemleri.tumOdemeTipleriniGetir()

        val lm = LinearLayoutManager(this)
        lm.orientation = LinearLayoutManager.VERTICAL
        binding.rvOdemeTipleri.layoutManager = lm
        binding.rvOdemeTipleri.addItemDecoration(DividerItemDecoration(this, lm.orientation))

        binding.rvOdemeTipleri.adapter = OdemeTipiAdapter(this, odemeTipiListesi, this::itemOnClick)
    }

    fun btnYeniOdemeTipiEkle_OnClick(view: View) {
        val intent = Intent(this, YeniOdemeTipiEkle::class.java)
        resultLauncher.launch(intent)
    }

    fun itemOnClick(position: Int) {
        val intent = Intent(this, MevcutOdemeTipi::class.java)
        intent.putExtra("odemeTipiId", odemeTipiListesi.get(position).Id)
        resultLauncher.launch(intent)

    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {
        if (it.resultCode == RESULT_OK) {
            odemeTipiListesi.clear()
            odemeTipiListesi.addAll(odemeTipiIslemleri.tumOdemeTipleriniGetir())
            binding.rvOdemeTipleri.adapter!!.notifyDataSetChanged()
        }
    }
}