package com.example.loginsqlitekotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.loginsqlitekotlin.model.PenggunaModel

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
        //        role
        const val TABLE_NAME_ROLE = "role"
        const val COLUMN_ID_ROLE = "idrole"
        const val COLUMN_ROLE_ROLE = "role"
        const val COLUMN_STATUS_ROLE = "status"
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

        val createTableQuery3 = "CREATE TABLE ${TABLE_NAME_ROLE} (" +
                "${COLUMN_ID_ROLE} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${COLUMN_ROLE_ROLE} TEXT," +
                "${COLUMN_STATUS_ROLE} TEXT)"

        db?.execSQL(createTableQuery3)
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

    fun readUser(username: String, password: String): PenggunaModel{
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(username, password)
        val cursor = db.query(TABLE_NAME,null,selection,selectionArgs, null, null,null)
        if (cursor.moveToFirst()){
            do {
                if (cursor != null){
                    val idpengguna = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                    val username = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME))
                    val password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD))
                    val namaPengguna = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA_PENGGUNA))
                    val role = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ROLE))
                    val status = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS))
                    val foto = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE))
                    return PenggunaModel(idpengguna, username, password, namaPengguna, role, status, foto)
                }
            } while (cursor.moveToNext())
        }
        cursor.close()
        return PenggunaModel(0, "", "", "", 0, "", "")
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
        val db = writableDatabase
        return db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }

    fun getUserById(id: Int): PenggunaModel{
        val db = readableDatabase
        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(id.toString())
        val cursor = db.query(TABLE_NAME,null,selection,selectionArgs, null, null,null)
        if (cursor.moveToFirst()){
            do {
                if (cursor != null){
                    val idpengguna = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                    val username = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME))
                    val password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD))
                    val namaPengguna = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA_PENGGUNA))
                    val role = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ROLE))
                    val status = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS))
                    val foto = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE))
                    return PenggunaModel(idpengguna, username, password, namaPengguna, role, status, foto)
                }
            } while (cursor.moveToNext())
        }
        cursor.close()
        return PenggunaModel(0, "", "", "", 0, "", "")
    }
}