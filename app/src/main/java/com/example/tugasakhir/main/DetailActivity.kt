package com.example.tugasakhir.main

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.tugasakhir.R
import com.example.tugasakhir.model.DataItem
import com.google.gson.Gson

class DetailActivity : AppCompatActivity() {

    private val STORAGE_PERMISSION_CODE = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getData()

        // tombol popup infografis
        val popInfo = findViewById<ConstraintLayout>(R.id.popup_infografis)
        popInfo.setOnClickListener {
           popupInfografis()
        }

        // tombol back
        val btnBack = findViewById<Button>(R.id.btn_backDetail)
        btnBack.setOnClickListener {
            finish()
        }

        // tombol download file pdf
        val btn_download = findViewById<Button>(R.id.btn_pdf)
        btn_download.setOnClickListener {
            if (checkStoragePermission()) {
                showDownloadConfirmationDialog()
            } else {
                requestStoragePermission()
            }
        }
    }

    private fun popupInfografis() {
        var popupDialog = Dialog(this)
        popupDialog.setCancelable(false)
        popupDialog.setContentView(R.layout.popup_infografis)
        popupDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val data = intent.getStringExtra("extra")
        val materi = Gson().fromJson<DataItem>(data, DataItem::class.java)
        val popInfo = popupDialog.findViewById<ImageView>(R.id.popIfografis)
        val imageInfo = "https://webapiandro.000webhostapp.com/storage/infografis/" + materi.infografis
        Glide.with(this)
            .load(imageInfo)
            .into(popInfo)

        val btnClose = popupDialog.findViewById<Button>(R.id.popTutup)
        btnClose.setOnClickListener {
            popupDialog.dismiss()
        }
        popupDialog.show()
    }

    private fun checkStoragePermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestStoragePermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            STORAGE_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            STORAGE_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showDownloadConfirmationDialog()
                } else {
                    Toast.makeText(
                        this,
                        "Izin akses penyimpanan diperlukan untuk mengunduh file.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun getData() {
        // ambil data dari intent
        val data = intent.getStringExtra("extra")
        val materi = Gson().fromJson<DataItem>(data, DataItem::class.java)

        // binding data ke view
        val tvdBab = findViewById<TextView>(R.id.tvd_bab)
        val tvdJudul = findViewById<TextView>(R.id.tvd_judul)
        val tvdDesc = findViewById<TextView>(R.id.tvd_deskripsi)
        val tvdKeyword = findViewById<TextView>(R.id.tvd_keyword)
        val ivdCover = findViewById<ImageView>(R.id.ivd_cover)
        val wvMateri = findViewById<WebView>(R.id.wvd_materi)

        // set data ke view
        tvdBab.text = materi.bab
        tvdJudul.text = materi.judul
        tvdDesc.text = "Deskripsi: " + materi.deskripsi
        tvdKeyword.text = "Keyword: " + materi.keyword
        ivdCover.setImageResource(0)
        val imageCover =  "https://webapiandro.000webhostapp.com/storage/cover/" + materi.cover
        Glide.with(this)
            .load(imageCover)
            .into(ivdCover)

        val linkVideo = materi.linkVideo
        val html = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"$linkVideo\" frameborder=\"0\" allowfullscreen></iframe></body></html>"
        wvMateri.settings.javaScriptEnabled = true
        wvMateri.webChromeClient = WebChromeClient()

        wvMateri.loadData(html, "text/html", "UTF-8")

    }




    private fun downloadFile(url: String, namaFile: String) {
        val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle("File Download")
            .setDescription("Downloading...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, namaFile)

        downloadManager.enqueue(request)
        // cek download berhasil atau tidak
        if (downloadManager != null) {
            Toast.makeText(this, "File berhasil diunduh", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "File gagal diunduh", Toast.LENGTH_SHORT).show()
        }
    }
    private fun showDownloadConfirmationDialog() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Konfirmasi Unduhan")
            .setMessage("Anda yakin ingin mengunduh file ini?")
            .setPositiveButton("Ya") { dialog, which ->
                // Pengguna mengkonfirmasi, mulai unduhan
                val data = intent.getStringExtra("extra")
                val materi = Gson().fromJson<DataItem>(data, DataItem::class.java)
                val namaFile = materi.filepdf
                val url = "https://webapiandro.000webhostapp.com/storage/filepdf/" + materi.filepdf
                downloadFile(url, namaFile!!)
            }
            .setNegativeButton("Tidak", null)
            .create()

        alertDialog.show()
    }
}