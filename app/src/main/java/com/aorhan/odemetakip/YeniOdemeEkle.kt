package com.aorhan.odemetakip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aorhan.odemetakip.databinding.ActivityYeniOdemeEkleBinding
import com.aorhan.odemetakip.entities.Odeme

class YeniOdemeEkle : AppCompatActivity() {

    lateinit var binding: ActivityYeniOdemeEkleBinding

    var odeme: Odeme? = null
    var odemeIslemleri: OdemeIslemleri

    init {
        odemeIslemleri = OdemeIslemleri(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYeniOdemeEkleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val odemeId = intent.getIntExtra("odemeId", -1)
        if (odemeId == -1) {
            odeme = Odeme()
            binding.btnOdemeSil.visibility = View.GONE
        } else {
            odeme = odemeIslemleri.OdemeGetir(odemeId)
            binding.editOAd.setText(odeme!!.OdemeAdi)
            binding.editOTutar.setText(odeme!!.Tutar)
            //Tarih eklenecek.

            binding.btnOdemeSil.visibility = View.VISIBLE
        }
    }

    fun btnOdemeKaydet_OnClick(view: View) {
        odeme!!.OdemeAdi = binding.editOAd.text.toString()
        odeme!!.Tutar = binding.editOTutar.text.toString()

        if (odeme!!.Id == null) {
            odemeIslemleri.odemeEkle(odeme!!)
        } else {
            odemeIslemleri.odemeGuncelle(odeme!!)
        }

        setResult(RESULT_OK)
        finish()
    }

    fun btnOdemeSil_OnClick(view: View) {
        odemeIslemleri.odemeSil(odeme!!.Id!!)
        setResult(RESULT_OK)
        finish()
    }

}