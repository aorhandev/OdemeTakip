package com.aorhan.odemetakip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aorhan.odemetakip.entities.OdemeTipi
import com.aorhan.odemetakip.databinding.ActivityYeniOdemeTipiEkleBinding

class YeniOdemeTipiEkle : AppCompatActivity() {

    lateinit var binding: ActivityYeniOdemeTipiEkleBinding

    var odemeTipi: OdemeTipi? = null
    var odemeTipiIslemleri: OdemeTipiIslemleri

    init {
        odemeTipiIslemleri = OdemeTipiIslemleri(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYeniOdemeTipiEkleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val odemeTipiId = intent.getIntExtra("odemeTipiId", -1)
        if (odemeTipiId == -1) {
            odemeTipi = OdemeTipi()
            binding.btnSil.visibility = View.GONE
        }else{
            odemeTipi = odemeTipiIslemleri.odemeTipiGetir(odemeTipiId)
            binding.editOtAd.setText(odemeTipi!!.OdemeTipiAdi)
            binding.editOtPeriyod.setText(odemeTipi!!.Periyod)
            //Spinner eklenecek.

            binding.btnSil.visibility = View.VISIBLE
        }
    }

    fun btnKaydet_OnClick(view: View) {
        odemeTipi!!.OdemeTipiAdi =  binding.editOtAd.text.toString()
        odemeTipi!!.Periyod =  binding.editOtPeriyod.text.toString()

        if(odemeTipi!!.Id == null){
            odemeTipiIslemleri.odemeTipiEkle(odemeTipi!!)
        }else{
            odemeTipiIslemleri.odemeTipiGuncelle(odemeTipi!!)
        }

        setResult(RESULT_OK)
        finish()
    }
    fun btnSil_OnClick(view: View) {
        odemeTipiIslemleri.odemeTipiSil(odemeTipi!!.Id!!)
        setResult(RESULT_OK)
        finish()
    }
}