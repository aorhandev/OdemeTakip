package com.aorhan.odemetakip

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.aorhan.odemetakip.entities.Odeme
import com.aorhan.odemetakip.entities.OdemeTipi

class OdemeIslemleri(context: Context) {

    var dbOpenHelper: DatabaseOpenHelper
    var dbOdemeIslemleri: SQLiteDatabase? = null

    init {
        dbOpenHelper = DatabaseOpenHelper(context, "OdemeDB", null, 1)
    }

    fun open() {
        dbOdemeIslemleri = dbOpenHelper.writableDatabase
    }

    fun close() {
        if (dbOdemeIslemleri != null && dbOdemeIslemleri!!.isOpen) {
            dbOdemeIslemleri!!.close()
        }
    }

    fun odemeEkle(odeme: Odeme) {
        val cv = ContentValues()
        cv.put("OdemeAdi", odeme.OdemeAdi)
        cv.put("Tutar", odeme.Tutar)
        cv.put("OdemeTarihi", odeme.OdemeTarihi)

        open()
        dbOdemeIslemleri!!.insert("Odemeler", null, cv)
        close()

    }

    fun odemeGuncelle(odeme: Odeme) {
        val cv = ContentValues()
        cv.put("OdemeAdi", odeme.OdemeAdi)
        cv.put("Tutar", odeme.Tutar)
        cv.put("OdemeTarihi", odeme.OdemeTarihi)

        open()
        dbOdemeIslemleri!!.update("Odemeler", cv, "Id =?", arrayOf(odeme.Id.toString()))
        close()

    }

    fun odemeSil(id: Int) {
        open()
        dbOdemeIslemleri!!.delete("Odemeler", "Id = ?", arrayOf(id.toString()))
        close()
    }

    private fun tumOdemeleriGetirSorgu(): Cursor {
        var sorgu = "Select * from  Odemeler"
        return dbOdemeIslemleri!!.rawQuery(sorgu, null)
    }


    @SuppressLint("Range")
    fun tumOdemeleriGetir(): ArrayList<Odeme> {
        val odemeListesi: ArrayList<Odeme> = ArrayList()
        var odeme: Odeme
        open()
        val c: Cursor = tumOdemeleriGetirSorgu()
        if (c.moveToFirst()) {
            do {
                odeme = Odeme()
                odeme.Id = c.getInt(0)
                odeme.OdemeAdi = c.getString(c.getColumnIndex("OdemeAdi"))
                odeme.Tutar = c.getString(c.getColumnIndex("Tutar"))
                odeme.OdemeTarihi = c.getString(c.getColumnIndex("OdemeTarihi"))
                odemeListesi.add(odeme)

            } while (c.moveToNext())
        }
        close()
        return odemeListesi
    }

    private fun OdemeGetirSorgu(id: Int): Cursor {
        var sorgu = "Select * from  Odemeler where Id= ?"
        return dbOdemeIslemleri!!.rawQuery(sorgu, arrayOf(id.toString()))
    }


    @SuppressLint("Range")
    fun OdemeGetir(id: Int): Odeme? {
        var odeme: Odeme? = null
        open()
        val c: Cursor = OdemeGetirSorgu(id)
        if (c.moveToFirst()) {
            odeme = Odeme()
            odeme.Id = c.getInt(0)
            odeme.OdemeAdi = c.getString(c.getColumnIndex("OdemeAdi"))
            odeme.Tutar = c.getString(c.getColumnIndex("Tutar"))
            odeme.OdemeTarihi = c.getString(c.getColumnIndex("OdemeTarihi"))
        }
        close()
        return odeme
    }
}