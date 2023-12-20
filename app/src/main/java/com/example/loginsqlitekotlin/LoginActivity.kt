package com.example.loginsqlitekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.loginsqlitekotlin.databinding.ActivityLoginBinding
import android.content.SharedPreferences

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var penggunaController: PenggunaController
    private lateinit var warungController: WarungController
    private lateinit var sharedpreferences: SharedPreferences

    companion object {
        const val SHARED_PREFS = "shared_prefs"
        const val NAMA_KEY = "pengguna_key"
        const val ID_KEY = "id_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedpreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)

        penggunaController = PenggunaController(this)
        warungController = WarungController(this)

        binding.loginButton.setOnClickListener{
            Log.v("DEBUGGING", "LOGIN BUTTON")

            val loginUsername = binding.loginUsername.text.toString()
            val loginPassword = binding.loginPassword.text.toString()
            loginDatabase(loginUsername, loginPassword)
        }

        binding.signupRedirectText.setOnClickListener{
            Log.v("DEBUGGING", "SIGN UP REDIRECT")
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginDatabase(username: String, password: String) {
        val userExist = penggunaController.readUser(username, password)

        val editor = sharedpreferences.edit()
        editor.putString(NAMA_KEY, userExist?.getNamaPengguna())
        editor.putInt(ID_KEY, userExist?.getIdPengguna()!!)
        editor.apply()


        if (userExist != null) {
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }
}