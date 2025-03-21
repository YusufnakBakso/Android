package com.example.ambafood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val nohpEditText = findViewById<EditText>(R.id.nohpEditText)
        val registerButton = findViewById<Button>(R.id.loginButton)
        val loginLink = findViewById<TextView>(R.id.registerLink)

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val nohp = nohpEditText.text.toString()

            if (email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty() && nohp.isNotEmpty()) {
                // Simpan data ke SharedPreferences sebagai simulasi database
                val sharedPref = getSharedPreferences("USER_DATA", MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putString("EMAIL", email)
                    putString("PASSWORD", password)
                    apply()
                }

                Toast.makeText(this, "Registrasi Berhasil! Silakan login.", Toast.LENGTH_SHORT).show()

                // Pindah ke halaman login
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Harap isi semua kolom!", Toast.LENGTH_SHORT).show()
            }
        }

        loginLink.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}