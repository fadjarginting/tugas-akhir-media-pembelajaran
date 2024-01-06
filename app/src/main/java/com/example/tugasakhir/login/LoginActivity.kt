package com.example.tugasakhir.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.tugasakhir.R
import com.example.tugasakhir.api.ApiConfig
import com.example.tugasakhir.main.MainActivity
import com.example.tugasakhir.model.ResponseLogin
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener {
            login()
        }

        val tvRegister = findViewById<TextView>(R.id.tv_register)
        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    private fun login() {
        val email = findViewById<TextView>(R.id.et_email_login)
        val password = findViewById<TextView>(R.id.et_password_login)

        if (email.text.toString().isEmpty()) {
            email.error = "Email tidak boleh kosong"
            email.requestFocus()
            return
        }else if (password.text.toString().isEmpty()) {
            password.error = "Password tidak boleh kosong"
            password.requestFocus()
            return
        }
        val pb = findViewById<ProgressBar>(R.id.progressBar2)
        pb.visibility = ConstraintLayout.VISIBLE
        ApiConfig.getService().login(email.text.toString(), password.text.toString()).enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                pb.visibility = ConstraintLayout.GONE
                val respon = response.body()!!
                if(respon.data?.token != null){
                    Toast.makeText(
                        this@LoginActivity,
                        "Login Berhasil: Selamat datang "+ respon.data.user?.name,
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    val user = Gson().toJson(respon.data.user)
                    intent.putExtra("extra", user)
                    startActivity(intent)
                    finish()
                }else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Error "+respon.status+": "+respon.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                pb.visibility = ConstraintLayout.GONE
                Toast.makeText(
                    this@LoginActivity,
                    "Error "+t.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}