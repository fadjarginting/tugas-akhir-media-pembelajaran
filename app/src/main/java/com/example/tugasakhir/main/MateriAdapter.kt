package com.example.tugasakhir.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tugasakhir.model.DataItem
import com.example.tugasakhir.R

class MateriAdapter (val dataMateri:List<DataItem?>?) : RecyclerView.Adapter<MateriAdapter.MyViewHolder>() {
    class MyViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val coverMateri = view.findViewById<ImageView>(R.id.imageMateri)
        val babMateri = view.findViewById<TextView>(R.id.tvJudulMateri)
        val judulMateri = view.findViewById<TextView>(R.id.tvDescMateri)
        val pb = view.findViewById<ProgressBar>(R.id.pb_cover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_materi, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (dataMateri != null){
            return dataMateri.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.babMateri.text = dataMateri?.get(position)?.bab
        holder.judulMateri.text = dataMateri?.get(position)?.judul

        if (dataMateri?.get(position)?.cover != null){
            holder.coverMateri.setImageResource(0)
            val gambarmateri = "http://192.168.1.5/androidapi/public/storage/cover/" + dataMateri.get(position)?.cover
            Glide.with(holder.itemView.context)
                .load(gambarmateri)
                .into(holder.coverMateri)
            holder.pb.visibility = View.GONE
        }
        //set on click listener pindah ke detail
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("bab", dataMateri?.get(position)?.bab)
            intent.putExtra("judul", dataMateri?.get(position)?.judul)
            intent.putExtra("deskripsi", dataMateri?.get(position)?.deskripsi)
            intent.putExtra("keyword", dataMateri?.get(position)?.keyword)
            intent.putExtra("cover", dataMateri?.get(position)?.cover)
            holder.itemView.context.startActivity(intent)
            Toast.makeText(holder.itemView.context, "Anda memilih ${dataMateri?.get(position)?.judul}", Toast.LENGTH_SHORT).show()
        }

    }
}