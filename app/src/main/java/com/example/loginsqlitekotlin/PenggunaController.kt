package com.example.loginsqlitekotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PenggunaController(private val context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object {
        const val DATABASE_NAME = "UserDatabase.db"
        const val DATABASE_VERSION = 2
//        pengguna
        const val TABLE_NAME = "Pengguna"
        const val COLUMN_ID = "idpengguna"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_NAMA_PENGGUNA = "namaPengguna"
        const val COLUMN_ROLE = "idrole"
        const val COLUMN_STATUS = "status"
        const val COLUMN_IMAGE = "foto"
//        warung
        const val TABLE_NAME_WARUNG = "Warung"
        const val COLUMN_ID_WARUNG = "idwarung"
        const val COLUMN_NAMA_WARUNG = "namawarung"
        const val COLUMN_LOGO_WARUNG = "logo"
        const val COLUMN_GAMBAR_WARUNG = "gambar"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_USERNAME TEXT," +
                "$COLUMN_PASSWORD TEXT," +
                "$COLUMN_NAMA_PENGGUNA TEXT," +
                "$COLUMN_ROLE INTEGER," +
                "$COLUMN_STATUS INTEGER," +
                "$COLUMN_IMAGE TEXT)"

        val createTableQuery2 = "CREATE TABLE ${TABLE_NAME_WARUNG} (" +
                "${COLUMN_ID_WARUNG} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${COLUMN_NAMA_WARUNG} TEXT," +
                "${COLUMN_LOGO_WARUNG} TEXT," +
                "${COLUMN_GAMBAR_WARUNG} TEXT)"

        db?.execSQL(createTableQuery2)

        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertUser(username: String, password: String):Long {
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
            put(COLUMN_NAMA_PENGGUNA, "owner")
            put(COLUMN_ROLE, 1)
            put(COLUMN_STATUS, "aktif")
            put(COLUMN_IMAGE, "")
        }
        val db = this.writableDatabase
        return db.insert(TABLE_NAME, null, values)
    }

    fun readUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(username, password)
        val cursor = db.query(TABLE_NAME,null,selection,selectionArgs, null, null,null)
        val userExist = cursor.count > 0;
        cursor.close()
        return userExist;
    }

    fun editUser(id: Int, username: String, password: String, namaPengguna: String, role: Int, status: String, foto: String):Int {
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
            put(COLUMN_NAMA_PENGGUNA, namaPengguna)
            put(COLUMN_ROLE, role)
            put(COLUMN_STATUS, status)
            put(COLUMN_IMAGE, foto)
        }
        val db = this.writableDatabase
        return db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }
}