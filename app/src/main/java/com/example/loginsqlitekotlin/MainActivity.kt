package com.example.loginsqlitekotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
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


    override fun onCreate(savedInstanceState: Bundle?) {
        warungController = WarungController(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val burjoHolicCard: CardView = findViewById(R.id.cardView13)
        burjoHolicCard.setOnClickListener(this)

        val vanJavaCard: CardView = findViewById(R.id.cardView14)
        vanJavaCard.setOnClickListener(this)

        val tomasCard: CardView = findViewById(R.id.cardView15)
        tomasCard.setOnClickListener(this)

        val AlifButton: Button = findViewById(R.id.alif_button)
        AlifButton.setOnClickListener(this)

        val AlifButton2: Button = findViewById(R.id.alif_button_2)
        AlifButton2.setOnClickListener(this)

        listWarungs.addAll(warungController.readWarung())
        rvWarungs = findViewById(R.id.rvWarungs)
        rvWarungs.setHasFixedSize(true)
        rvWarungs.setLayoutManager(LinearLayoutManager(this))
        val listWarungAdapter = ListWarungAdapter(listWarungs)
        rvWarungs.setAdapter(listWarungAdapter)
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.cardView13 -> {
                    val holicIntent = Intent(this, HolicActivity::class.java)
                    startActivity(holicIntent)
                }
                R.id.cardView14 -> {
                    val vanJavaIntent = Intent(this, VanJavaActivity::class.java)
                    startActivity(vanJavaIntent)
                }
                R.id.cardView15 -> {
                    val tomasIntent = Intent(this, TomasActivity::class.java)
                    startActivity(tomasIntent)
                }
                R.id.alif_button -> {
                    val alifIntent = Intent(this, CrudPengguna::class.java)
                    startActivity(alifIntent)
                }
                R.id.alif_button_2 -> {
                    val alifIntent2 = Intent(this, CrudRole::class.java)
                    startActivity(alifIntent2)
                }
            }
        }
    }

}
