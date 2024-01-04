package com.example.tugasakhir.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import com.example.tugasakhir.R
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.tugasakhir.api.ApiConfig
import com.example.tugasakhir.main.HomeFragment
import com.example.tugasakhir.main.MainActivity
import com.example.tugasakhir.model.ResponseRegister
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {
    private lateinit var etPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister = findViewById<Button>(R.id.btn_registerr)
        btnRegister.setOnClickListener {
            register()
        }

        val tvLogin = findViewById<TextView>(R.id.tv_login)
        tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    fun register(){
        val et_nama = findViewById<EditText>(R.id.et_nama)
        val et_email = findViewById<EditText>(R.id.et_email)
        val et_password = findViewById<EditText>(R.id.et_password)
        if (et_nama.text.toString().isEmpty()){
            et_nama.error = "Masukkan Nama"
            et_nama.requestFocus()
            return
        } else if (et_email.text.toString().isEmpty()){
            et_email.error = "Masukkan Email"
            et_email.requestFocus()
            return
        } else if (et_password.text.toString().isEmpty()){
            et_password.error = "Masukkan Password"
            et_password.requestFocus()
            return
        }
        val pbregister = findViewById<ProgressBar>(R.id.progressBar1)
        pbregister.visibility = ProgressBar.VISIBLE
        ApiConfig.getService().register(et_nama.text.toString(), et_email.text.toString(), et_password.text.toString()).enqueue(object : Callback<ResponseRegister> {
            override fun onResponse(call: Call<ResponseRegister>, response: Response<ResponseRegister>) {
                pbregister.visibility = ProgressBar.GONE
                val respon = response.body()!!
                if (respon.status == 200){
                    Toast.makeText(this@RegisterActivity, "Register Berhasil: Selamat Datang "+ (respon.user?.name), Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@RegisterActivity, "Error "+respon.status +" :"+respon.message, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                pbregister.visibility = ProgressBar.GONE
                Toast.makeText(this@RegisterActivity, "Error  "+t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}


