package com.example.loginsqlitekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.loginsqlitekotlin.databinding.ActivityCrudRoleBinding
import com.example.loginsqlitekotlin.databinding.ActivityLoginBinding


class CrudRole : AppCompatActivity() {

    private lateinit var binding: ActivityCrudRoleBinding
    private lateinit var RoleController: RoleController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrudRoleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        RoleController = RoleController(this)

        binding.btAdd.setOnClickListener{
            Log.v("DEBUGGING", "ADD BUTTON")
            val role = binding.etRole.text.toString()
            val status = binding.etStatus.text.toString()
            insertRole(role, status)
            Log.v("DEBUGGING", "ADD BUTTON")
        }


    }

    private fun insertRole(role: String, status: String) {
        val insertRole = RoleController.insertRole(role, status)
        if (insertRole != 1L){
            Toast.makeText( this,"berhasil menambahkan role",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText( this,"gagal menambahkan role",Toast.LENGTH_SHORT).show()
        }
    }
}