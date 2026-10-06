package com.example.latihan

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var tvRole: TextView

    private val roleLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedRole = result.data?.getStringExtra("SELECTED_ROLE")
            selectedRole?.let {
                tvRole.text = it
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val layoutEmail = findViewById<LinearLayout>(R.id.layoutEmail)
        val layoutPhone = findViewById<LinearLayout>(R.id.layoutPhone)
        val layoutRole = findViewById<LinearLayout>(R.id.layoutRole)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvPhone = findViewById<TextView>(R.id.tvPhone)
        tvRole = findViewById(R.id.tvRole)

        // 1. Kirim Email saat diklik
        layoutEmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${tvEmail.text}")
            }
            startActivity(emailIntent)
        }

        // 2. Panggilan Telepon saat diklik
        layoutPhone.setOnClickListener {
            val phoneIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${tvPhone.text}")
            }
            startActivity(phoneIntent)
        }

        // 3. Pindah ke halaman RoleSelectionActivity saat Role diklik
        layoutRole.setOnClickListener {
            val intent = Intent(this, RoleSelectionActivity::class.java)
            roleLauncher.launch(intent)
        }
    }
}