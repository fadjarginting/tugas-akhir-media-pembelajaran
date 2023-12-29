package com.example.tugasakhir.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.tugasakhir.R
import com.example.tugasakhir.main.MainActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mainBtnClick()

    }
    private fun mainBtnClick() {
        val btnLogin: Button = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
        val tvRegister = findViewById<TextView>(R.id.tv_register)
        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}