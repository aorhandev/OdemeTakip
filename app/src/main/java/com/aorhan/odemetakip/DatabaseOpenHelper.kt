package com.aorhan.odemetakip

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseOpenHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(p0: SQLiteDatabase?) {
        var sorgu1 = "CREATE TABLE OdemeTipleri(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, OdemeTipiAdi TEXT, Periyod TEXT, PeriyodSuresi INTEGER)"
        var sorgu2 = "CREATE TABLE Odemeler(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, OdemeAdi TEXT, Tutar TEXT, OdemeTarihi TEXT, FOREIGN KEY (OdemeTipiId) REFERENCES OdemeTipleri(Id) )"

        p0!!.execSQL(sorgu1)
        p0!!.execSQL(sorgu2)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}