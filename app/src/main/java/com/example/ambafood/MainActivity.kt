package com.example.ambafood

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val registerLink = findViewById<TextView>(R.id.registerLink)

        // Cek apakah user sudah login sebelumnya
        val sharedPref = getSharedPreferences("USER_DATA", MODE_PRIVATE)
        val savedEmail = sharedPref.getString("EMAIL", null)
        val savedPassword = sharedPref.getString("PASSWORD", null)

        // Paksa agar login selalu muncul
        val isLoggedIn = false  // Mengabaikan status login sebelumnya

        if (isLoggedIn) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish() // Tutup MainActivity agar tidak bisa kembali
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email == savedEmail && password == savedPassword) {
                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()

                // Simpan status login
                val editor = sharedPref.edit()
                editor.putBoolean("IS_LOGGED_IN", true)
                editor.apply()

                // Navigasi ke HomeActivity
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish() // Tutup MainActivity agar tidak bisa kembali
            } else {
                Toast.makeText(this, "Email atau Password salah!", Toast.LENGTH_SHORT).show()
            }
        }

        // Implementasi underline & klik pada "Register"
        val text = "Don't have an account? Register"
        val spannable = SpannableString(text)
        val startIndex = text.indexOf("Register")

        spannable.setSpan(UnderlineSpan(), startIndex, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@MainActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }, startIndex, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        registerLink.text = spannable
        registerLink.movementMethod = LinkMovementMethod.getInstance()
    }
}
