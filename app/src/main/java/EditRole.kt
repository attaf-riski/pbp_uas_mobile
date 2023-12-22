package com.example.loginsqlitekotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginsqlitekotlin.MainActivity
import com.example.loginsqlitekotlin.PenggunaController
import com.example.loginsqlitekotlin.R
import com.example.loginsqlitekotlin.RoleController
import com.example.loginsqlitekotlin.databinding.ActivityEditRoleBinding


class EditRole : AppCompatActivity(), View.OnClickListener {
    private lateinit var roleController: RoleController
    private lateinit var binding: ActivityEditRoleBinding

    private lateinit var etIdRole: EditText
    private lateinit var etRole: EditText
    private lateinit var etStatus: EditText
    private lateinit var buttonEdit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRoleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        roleController = RoleController(this)

        etIdRole = findViewById(R.id.et_id)
        etRole = findViewById(R.id.et_role)
        etStatus = findViewById(R.id.et_status)
        buttonEdit = findViewById(R.id.bt_edit)
        buttonEdit.setOnClickListener(this)

        // Retrieve user ID from intent
        val id = intent.getIntExtra("USER_ID", 0)

        // Fetch user details using the ID
        val role = roleController.getRoleById(id)

        // Populate EditText fields with user details
        etIdRole.setText(role.getIdRole().toString())
        etRole.setText(role.getRole())
        etStatus.setText(role.getStatus())
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.bt_edit -> {
                    val id = etIdRole.text.toString().toInt()
                    val role = etRole.text.toString()
                    val status = etStatus.text.toString()

                    val result = roleController.editRole(id, role, status)

                    if (result > 0) {
                        Toast.makeText(this, "Edit Role Berhasil", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Gagal mengedit Role", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}