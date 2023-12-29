package com.example.tugasakhir

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MateriAdapter (val dataMateri:List<DataItem?>?) : RecyclerView.Adapter<MateriAdapter.MyViewHolder>() {
    class MyViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val imgMateri = view.findViewById<ImageView>(R.id.img_card)
        val judulMateri = view.findViewById<TextView>(R.id.judul_materi)
        val detailMateri = view.findViewById<TextView>(R.id.detail_materi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_mainv, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (dataMateri != null){
            return dataMateri.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.judulMateri.text = dataMateri?.get(position)?.judul
        holder.detailMateri.text = dataMateri?.get(position)?.materi

        Glide.with(holder.itemView.context)
            .load(dataMateri?.get(position)?.gambar)
            .into(holder.imgMateri)

        //set on click listener pindah ke detail
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Anda memilih ${dataMateri?.get(position)?.judul}", Toast.LENGTH_SHORT).show()
        }

    }

    class Picasso {
        companion object {
            fun get(): Any {
                TODO("Not yet implemented")
            }
        }

    }
}