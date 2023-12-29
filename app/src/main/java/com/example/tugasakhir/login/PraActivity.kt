package com.example.tugasakhir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class PraActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pra_relog)
        val btn_masuk: Button = findViewById(R.id.btn_masuk)
        btn_masuk.setOnClickListener {
            // Pindah ke laman kedua saat tombol diklik
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}