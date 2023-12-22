package com.example.loginsqlitekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginsqlitekotlin.databinding.ActivityCrudPenggunaBinding
import com.example.loginsqlitekotlin.databinding.ActivityCrudRoleBinding
import com.example.loginsqlitekotlin.model.PenggunaModel
import com.example.loginsqlitekotlin.model.WarungModel

class CrudPengguna : AppCompatActivity() {
    private lateinit var binding: ActivityCrudPenggunaBinding
    private lateinit var PenggunaController: PenggunaController

    private lateinit var rvUser: RecyclerView
    private var listUser = ArrayList<PenggunaModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrudPenggunaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PenggunaController = PenggunaController(this)



        binding.btAdd.setOnClickListener{
            Log.v("DEBUGGING", "ADD BUTTON")
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val name = binding.etNama.text.toString()
            val role = binding.etRole.text.toString().toInt()
            val status = binding.etStatus.text.toString()
            val foto = binding.etFoto.text.toString()
            insertUser2(username, password, name, role, status, foto)
            Log.v("DEBUGGING", "ADD BUTTON")
        }

        listUser.addAll(PenggunaController.readUser2())
        rvUser = findViewById(R.id.rv_list_pengguna)
        rvUser.setHasFixedSize(true)
        rvUser.setLayoutManager(LinearLayoutManager(this))
        val listUserAdapter = ListUserAdapter(listUser)
        rvUser.setAdapter(listUserAdapter)


    }

    private fun insertUser2(username: String, password: String, name: String, role: Int, status: String, foto: String) {
        val insertUser = PenggunaController.insertUser2(username, password, name, role, status, foto)
        if (insertUser != 1L){
            Toast.makeText( this,"berhasil menambahkan user", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText( this,"gagal menambahkan user", Toast.LENGTH_SHORT).show()
        }
    }
}

