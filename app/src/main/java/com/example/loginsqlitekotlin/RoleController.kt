package com.example.loginsqlitekotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.loginsqlitekotlin.model.PenggunaModel
import com.example.loginsqlitekotlin.model.RoleModel
class RoleController(private val context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object {
        const val DATABASE_NAME = "UserDatabase.db"
        const val DATABASE_VERSION = 2
        //        role
        const val TABLE_NAME = "role"
        const val COLUMN_ID = "idrole"
        const val COLUMN_ROLE = "role"
        const val COLUMN_STATUS = "status"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_ROLE TEXT," +
                "$COLUMN_STATUS TEXT)"

        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertRole(role: String, status: String):Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ROLE, role)
            put(COLUMN_STATUS, status)
        }
        val result = db.insert(TABLE_NAME, null, values)
        db.close()
        return result
    }

    fun readRole(role: String): RoleModel{
        val db = readableDatabase
        val selection = "$COLUMN_ROLE = ?"
        val selectionArgs = arrayOf(role)
        val cursor = db.query(TABLE_NAME,null,selection,selectionArgs, null, null,null)
        if (cursor.moveToFirst()){
            do {
                if (cursor != null){
                    val idrole = cursor.getInt(cursor.getColumnIndexOrThrow(RoleController.COLUMN_ID))
                    val role = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROLE))
                    val status = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS))
                    return RoleModel(idrole,role,status)
                }
            } while (cursor.moveToNext())
        }
        cursor.close()
        return RoleModel(0, "", "")
    }

    fun readRole2(): ArrayList<RoleModel> {
        val roleList = ArrayList<RoleModel>()
        val db = readableDatabase
        val query = "SELECT * FROM ${RoleController.TABLE_NAME}"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()){
            do {
                if (cursor != null){
                    val idrole = cursor.getInt(cursor.getColumnIndexOrThrow(RoleController.COLUMN_ID))
                    val role = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROLE))
                    val status = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS))
                    roleList.add(RoleModel(idrole,role,status))
                }
            } while (cursor.moveToNext())
        }
        cursor.close()
        return roleList
    }

    fun editRole(id: Int, role: String, status: String):Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ROLE, role)
            put(COLUMN_STATUS, status)
        }
        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(id.toString())
        val result = db.update(TABLE_NAME, values, selection, selectionArgs)
        db.close()
        return result
    }

    fun getRoleById(id: Int): RoleModel{
        val db = readableDatabase
        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(id.toString())
        val cursor = db.query(TABLE_NAME,null,selection,selectionArgs, null, null,null)
        if (cursor.moveToFirst()){
            do {
                if (cursor != null){
                    val idrole = cursor.getInt(cursor.getColumnIndexOrThrow(RoleController.COLUMN_ID))
                    val role = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROLE))
                    val status = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS))
                    return RoleModel(idrole,role,status)
                }
            } while (cursor.moveToNext())
        }
        cursor.close()
        return RoleModel(0, "", "")
    }
    fun deleteRole(idrole: Int): Any{
        val db = this.writableDatabase
        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(idrole.toString())
        val result = db.delete(TABLE_NAME, selection, selectionArgs)
        db.close()
        return result
    }
}