package com.example.ambafood

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView
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

        // Implementasi Underline dan ClickableSpan
        val registerLink = findViewById<TextView>(R.id.registerLink)
        val text = "Don't have an account? Register"

        val spannable = SpannableString(text)
        val startIndex = text.indexOf("Register")

        // Tambahkan underline pada kata "Register"
        spannable.setSpan(UnderlineSpan(), startIndex, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Jadikan "Register" bisa diklik
        spannable.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Pindah ke RegisterActivity
                val intent = Intent(this@MainActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }, startIndex, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        registerLink.text = spannable
        registerLink.movementMethod = LinkMovementMethod.getInstance()
    }
}
