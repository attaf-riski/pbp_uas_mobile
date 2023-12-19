package com.example.loginsqlitekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginsqlitekotlin.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var penggunaController: PenggunaController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        penggunaController = PenggunaController(this)

        binding.signupButton.setOnClickListener{
            val signupUsername = binding.signupUsername.text.toString()
            val signupPassword = binding.signupPassword.text.toString()
            signUpDatabase(signupUsername, signupPassword)
        }

        binding.loginRedirectText.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signUpDatabase(username: String, password: String) {
       val insertedRow = penggunaController.insertUser(username, password)
        if (insertedRow != -1L){
            Toast.makeText(this, "Sign Up Success", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        } else {
            Toast.makeText(this, "Sign Up Failed", Toast.LENGTH_SHORT).show()
        }
    }
}