package com.example.loginsqlitekotlin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginsqlitekotlin.databinding.ActivityMainBinding
import com.example.loginsqlitekotlin.model.WarungModel

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var rvWarungs: RecyclerView
    private var listWarungs = ArrayList<WarungModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var warungController: WarungController
    private lateinit var penggunaController: PenggunaController
    private lateinit var sharedpreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        warungController = WarungController(this)
        penggunaController = PenggunaController(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val namaPengguna : TextView = findViewById(R.id.namaPengguna)
        sharedpreferences = getSharedPreferences(LoginActivity.SHARED_PREFS, MODE_PRIVATE)
        val id = sharedpreferences.getInt(LoginActivity.ID_KEY, 0)
        val pengguna = penggunaController.getUserById(id)

        namaPengguna.text = pengguna.getNamaPengguna();

        val AlifButton: Button = findViewById(R.id.alif_button)
        AlifButton.setOnClickListener(this)

        val AlifButton2: Button = findViewById(R.id.alif_button_2)
        AlifButton2.setOnClickListener(this)

        val EditProfile: Button = findViewById(R.id.editProfile)
        EditProfile.setOnClickListener(this)

        listWarungs.addAll(warungController.readWarung())
        Log.d("Bangke", "listWarungs: ${listWarungs.size}")
        rvWarungs = findViewById(R.id.rvWarungs)
        rvWarungs.setHasFixedSize(true)
        rvWarungs.setLayoutManager(LinearLayoutManager(this))
        val listWarungAdapter = ListWarungAdapter(listWarungs)
        rvWarungs.setAdapter(listWarungAdapter)

//        tambah warung
        val tambahWarungButton: Button = findViewById(R.id.tambahWarung)
        tambahWarungButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.alif_button -> {
                    val alifIntent = Intent(this, CrudPengguna::class.java)
                    startActivity(alifIntent)
                }
                R.id.alif_button_2 -> {
                    val alifIntent2 = Intent(this, CrudRole::class.java)
                    startActivity(alifIntent2)
                }
                R.id.editProfile -> {
                    val editProfileIntent = Intent(this, EditPengguna::class.java)
                    startActivity(editProfileIntent)
                }
                R.id.tambahWarung -> {
                    val tambahWarungIntent = Intent(this, addwarung::class.java)
                    startActivity(tambahWarungIntent)
                }
            }
        }
    }

}
