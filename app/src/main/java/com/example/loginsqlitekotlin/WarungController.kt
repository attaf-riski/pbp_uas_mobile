package com.example.loginsqlitekotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.loginsqlitekotlin.model.WarungModel

class WarungController(private val context: Context): SQLiteOpenHelper(context,
    PenggunaController.DATABASE_NAME, null,
    PenggunaController.DATABASE_VERSION
) {
    companion object{
        const val DATABASE_NAME = "UserDatabase.db"
        const val DATABASE_VERSION = 2
        const val TABLE_NAME = "Warung"
        const val COLUMN_ID = "idwarung"
        const val COLUMN_NAMA_WARUNG = "namawarung"
        const val COLUMN_LOGO_WARUNG = "logo"
        const val COLUMN_GAMBAR_WARUNG = "gambar"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_NAMA_WARUNG TEXT," +
                "$COLUMN_LOGO_WARUNG TEXT," +
                "$COLUMN_GAMBAR_WARUNG TEXT)"

        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertWarung(namawarung: String, logo: String, gambar: String):Long {
        val values = ContentValues().apply {
            put(COLUMN_NAMA_WARUNG, namawarung)
            put(COLUMN_LOGO_WARUNG, logo)
            put(COLUMN_GAMBAR_WARUNG, gambar)
        }
        val db = this.writableDatabase
        return db.insert(TABLE_NAME, null, values)
    }

    fun readWarung(): ArrayList<WarungModel> {
        val listWarung = ArrayList<WarungModel>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()){
            do {
                if (cursor != null){
                    val idwarung = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                    val namawarung = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA_WARUNG))
                    val logo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOGO_WARUNG))
                    val gambar = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GAMBAR_WARUNG))
                    listWarung.add(WarungModel(idwarung, namawarung, logo, gambar))
                }
            } while (cursor.moveToNext())
        }
        cursor.close()
        return listWarung
    }

    fun readWarungById(idwarung: Int): ArrayList<WarungModel> {
        val listWarung = ArrayList<WarungModel>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $idwarung"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()){
            do {
                if (cursor != null){
                    val idwarung = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                    val namawarung = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA_WARUNG))
                    val logo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOGO_WARUNG))
                    val gambar = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GAMBAR_WARUNG))
                    listWarung.add(WarungModel(idwarung, namawarung, logo, gambar))
                }
            } while (cursor.moveToNext())
        }
        cursor.close()
        return listWarung
    }

    fun editWarung(idwarung: Int, namawarung: String, logo: String, gambar: String):Int {
        val values = ContentValues().apply {
            put(COLUMN_NAMA_WARUNG, namawarung)
            put(COLUMN_LOGO_WARUNG, logo)
            put(COLUMN_GAMBAR_WARUNG, gambar)
        }
        val db = this.writableDatabase
        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(idwarung.toString())
        return db.update(TABLE_NAME, values, selection, selectionArgs)
    }

    fun deleteWarung(idwarung: Int):Int {
        val db = this.writableDatabase
        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(idwarung.toString())
        return db.delete(TABLE_NAME, selection, selectionArgs)
    }
}