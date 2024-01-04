package com.example.tugasakhir.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.tugasakhir.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getData()
    }

    private fun getData() {
        val bab = intent.getStringExtra("bab")
        val judul = intent.getStringExtra("judul")
        val deskripsi = intent.getStringExtra("deskripsi")
        val keyword = intent.getStringExtra("keyword")
        val cover = intent.getStringExtra("cover")


        val tvdBab = findViewById<TextView>(R.id.tvd_bab)
        val tvdJudul = findViewById<TextView>(R.id.tvd_judul)
        val tvdDesc = findViewById<TextView>(R.id.tvd_deskripsi)
        val tvdKeyword = findViewById<TextView>(R.id.tvd_keyword)
        val ivdCover = findViewById<ImageView>(R.id.ivd_cover)

        tvdBab.text = bab
        tvdJudul.text = judul
        tvdDesc.text = "Deskripsi: "+ deskripsi
        tvdKeyword.text = "Keyword: "+ keyword
        ivdCover.setImageResource(0)
        val imageCover =  "http://192.168.1.5/androidapi/public/storage/cover/" + cover
        Glide.with(this)
            .load(imageCover)
            .into(ivdCover)
    }
}