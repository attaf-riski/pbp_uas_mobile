package com.example.loginsqlitekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.loginsqlitekotlin.databinding.ActivityAddwarungBinding
import com.example.loginsqlitekotlin.model.WarungModel

class addwarung : AppCompatActivity() {
    private lateinit var binding: ActivityAddwarungBinding
    private lateinit var etNamaWarung: EditText
    private lateinit var etWarung: EditText
    private lateinit var warungController: WarungController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddwarungBinding.inflate(layoutInflater)
        setContentView(binding.root)

        warungController = WarungController(this)

        binding.btnSubmit.setOnClickListener{
            val namawarung = binding.editTextWarung.text.toString()
            val logo = ""
            val gambar = ""
            insertWarung(namawarung, logo, gambar)
        }


    }

    private fun insertWarung(namawarung: String, logo: String, gambar: String) {
        val insertedRow = warungController.insertWarung(namawarung, logo, gambar)
        if (insertedRow != -1L){
            Toast.makeText(this, "Insert Warung Success", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        } else {
            Toast.makeText(this, "Insert Warung Failed", Toast.LENGTH_SHORT).show()
        }
    }
}