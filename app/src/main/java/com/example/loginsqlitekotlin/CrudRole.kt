package com.example.loginsqlitekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginsqlitekotlin.databinding.ActivityCrudRoleBinding
import com.example.loginsqlitekotlin.model.RoleModel


class CrudRole : AppCompatActivity() {

    private lateinit var binding: ActivityCrudRoleBinding
    private lateinit var RoleController: RoleController

    private lateinit var rvRole: RecyclerView
    private var listRole = ArrayList<RoleModel>()

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

        listRole.addAll(RoleController.readRole2())
        rvRole = findViewById(R.id.rv_list_role)
        rvRole.setHasFixedSize(true)
        rvRole.setLayoutManager(LinearLayoutManager(this))
        val listRoleAdapter = ListRoleAdapter(listRole)
        rvRole.setAdapter(listRoleAdapter)


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