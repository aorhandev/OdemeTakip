package com.aorhan.odemetakip

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.aorhan.odemetakip.entities.OdemeTipi

class OdemeTipiIslemleri(context: Context) {

    var dbOpenHelper: DatabaseOpenHelper
    var dbOdemeTipiIslemleri: SQLiteDatabase? = null

    init {
        dbOpenHelper = DatabaseOpenHelper(context, "OdemeDB", null, 1)
    }

    fun open() {
        dbOdemeTipiIslemleri = dbOpenHelper.writableDatabase
    }

    fun close() {
        if (dbOdemeTipiIslemleri != null && dbOdemeTipiIslemleri!!.isOpen) {
            dbOdemeTipiIslemleri!!.close()
        }
    }

    fun odemeTipiEkle(odemeTipi: OdemeTipi) {
        val cv = ContentValues()
        cv.put("OdemeTipiAdi", odemeTipi.OdemeTipiAdi)
        cv.put("Periyod", odemeTipi.Periyod)
        cv.put("PeriyodSuresi", odemeTipi.PeriyodSuresi)

        open()
        dbOdemeTipiIslemleri!!.insert("OdemeTipleri", null, cv)
        close()

    }

    fun odemeTipiGuncelle(odemeTipi: OdemeTipi) {
        val cv = ContentValues()
        cv.put("OdemeTipiAdi", odemeTipi.OdemeTipiAdi)
        cv.put("Periyod", odemeTipi.Periyod)
        cv.put("PeriyodSuresi", odemeTipi.PeriyodSuresi)

        open()
        dbOdemeTipiIslemleri!!.update("OdemeTipleri", cv, "Id =?", arrayOf(odemeTipi.Id.toString()))
        close()

    }

    fun odemeTipiSil(id: Int) {
        open()
        dbOdemeTipiIslemleri!!.delete("OdemeTipleri", "Id = ?", arrayOf(id.toString()))
        close()
    }

    private fun tumOdemeTipleriniGetirSorgu(): Cursor {
        var sorgu = "Select * from  OdemeTipleri"
        return dbOdemeTipiIslemleri!!.rawQuery(sorgu, null)
    }


    @SuppressLint("Range")
    fun tumOdemeTipleriniGetir(): ArrayList<OdemeTipi> {
        val odemeTipleriListesi: ArrayList<OdemeTipi> = ArrayList()
        var odemeTipi: OdemeTipi
        open()
        val c: Cursor = tumOdemeTipleriniGetirSorgu()
        if (c.moveToFirst()) {
            do {
                odemeTipi = OdemeTipi()
                odemeTipi.Id = c.getInt(0)
                odemeTipi.OdemeTipiAdi = c.getString(c.getColumnIndex("OdemeTipiAdi"))
                odemeTipi.Periyod = c.getString(c.getColumnIndex("Periyod"))
                odemeTipi.PeriyodSuresi = c.getInt(c.getColumnIndex("PeriyodSuresi"))
                odemeTipleriListesi.add(odemeTipi)

            } while (c.moveToNext())
        }
        close()
        return odemeTipleriListesi
    }

    private fun OdemeTipiGetirSorgu(id: Int): Cursor {
        var sorgu = "Select * from  OdemeTipleri where Id= ?"
        return dbOdemeTipiIslemleri!!.rawQuery(sorgu, arrayOf(id.toString()))
    }

    @SuppressLint("Range")
    fun odemeTipiGetir(id: Int): OdemeTipi? {
        var odemeTipi: OdemeTipi? = null
        open()
        val c: Cursor = OdemeTipiGetirSorgu(id)
        if (c.moveToFirst()) {
            odemeTipi = OdemeTipi()
            odemeTipi.Id = c.getInt(0)
            odemeTipi.OdemeTipiAdi = c.getString(c.getColumnIndex("OdemeTipiAdi"))
            odemeTipi.Periyod = c.getString(c.getColumnIndex("Periyod"))
            odemeTipi.PeriyodSuresi = c.getInt(c.getColumnIndex("PeriyodSuresi"))
        }
        close()
        return odemeTipi
    }
}