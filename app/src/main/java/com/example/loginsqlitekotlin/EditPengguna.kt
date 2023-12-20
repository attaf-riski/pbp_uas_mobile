package com.example.loginsqlitekotlin

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.loginsqlitekotlin.databinding.ActivityEditPenggunaBinding

class EditPengguna : AppCompatActivity(), View.OnClickListener {

    private lateinit var sharedpreferences: SharedPreferences
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

        sharedpreferences = getSharedPreferences(LoginActivity.SHARED_PREFS, MODE_PRIVATE)
        val idPengguna = sharedpreferences.getInt(LoginActivity.ID_KEY, 0)

        etId = findViewById<EditText>(R.id.et_id)
        etUsername = findViewById<EditText>(R.id.et_username)
        etPassword = findViewById<EditText>(R.id.et_password)
        etNamaPengguna = findViewById<EditText>(R.id.et_nama)
        etIdRole = findViewById<EditText>(R.id.et_role)
        etStatus = findViewById<EditText>(R.id.et_status)
        etFoto = findViewById<EditText>(R.id.et_foto)
        buttonEdit = findViewById<Button>(R.id.bt_edit)
        buttonEdit.setOnClickListener(this);

        penggunaController = PenggunaController(this)
        val pengguna = penggunaController.getUserById(idPengguna)

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

                    if(result > 0){
                        Toast.makeText(this, "Edit Pengguna Berhasil", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }

                }
            }
            }
    }
}