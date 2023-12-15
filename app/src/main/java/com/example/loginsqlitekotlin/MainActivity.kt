package com.example.loginsqlitekotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
