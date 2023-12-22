package com.example.loginsqlitekotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginsqlitekotlin.databinding.ActivityEditPenggunaBinding

class EditUser : AppCompatActivity(), View.OnClickListener {

    private lateinit var penggunaController: PenggunaController
    private lateinit var binding: ActivityEditPenggunaBinding

    private lateinit var etId: EditText
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etNamaPengguna: EditText
    private lateinit var etIdRole: EditText
    private lateinit var etStatus: EditText
    private lateinit var etFoto: EditText
    private lateinit var buttonEdit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPenggunaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        penggunaController = PenggunaController(this)

        etId = findViewById(R.id.et_id)
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        etNamaPengguna = findViewById(R.id.et_nama)
        etIdRole = findViewById(R.id.et_role)
        etStatus = findViewById(R.id.et_status)
        etFoto = findViewById(R.id.et_foto)
        buttonEdit = findViewById(R.id.bt_edit)
        buttonEdit.setOnClickListener(this)

        // Retrieve user ID from intent
        val userId = intent.getIntExtra("USER_ID", 0)

        // Fetch user details using the ID
        val pengguna = penggunaController.getUserById(userId)

        // Populate EditText fields with user details
        etId.setText(pengguna.getIdPengguna().toString())
        etUsername.setText(pengguna.getUsername())
        etPassword.setText(pengguna.getPassword())
        etNamaPengguna.setText(pengguna.getNamaPengguna())
        etIdRole.setText(pengguna.getIdRole().toString())
        etStatus.setText(pengguna.getStatus())
        etFoto.setText(pengguna.getFoto())
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.bt_edit -> {
                    val id = etId.text.toString().toInt()
                    val username = etUsername.text.toString()
                    val password = etPassword.text.toString()
                    val namaPengguna = etNamaPengguna.text.toString()
                    val idRole = etIdRole.text.toString().toInt()
                    val status = etStatus.text.toString()
                    val foto = etFoto.text.toString()

                    val result = penggunaController.editUser(id, username, password, namaPengguna, idRole, status, foto)

                    if (result > 0) {
                        Toast.makeText(this, "Edit Pengguna Berhasil", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Gagal mengedit pengguna", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}