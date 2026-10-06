package com.example.latihan

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RoleSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_role_selection)

        val btnAdmin = findViewById<Button>(R.id.btnAdmin)
        val btnUser = findViewById<Button>(R.id.btnUser)
        val btnGuest = findViewById<Button>(R.id.btnGuest)

        btnAdmin.setOnClickListener { sendRoleBack("Admin") }
        btnUser.setOnClickListener { sendRoleBack("User") }
        btnGuest.setOnClickListener { sendRoleBack("Guest") }
    }

    private fun sendRoleBack(selectedRole: String) {
        val resultIntent = Intent()
        resultIntent.putExtra("SELECTED_ROLE", selectedRole)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}