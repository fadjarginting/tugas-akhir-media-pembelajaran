package com.example.tugasakhir.main

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasakhir.R
import com.example.tugasakhir.model.ResponseMateri
import com.example.tugasakhir.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response



class HomeFragment : Fragment() {
    lateinit var mainAdapter: MateriAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val materi = view.findViewById<RecyclerView>(R.id.rv_materi_home)
        ApiConfig.getService().getMateri().enqueue(object : Callback<ResponseMateri> {
            override fun onResponse(call: Call<ResponseMateri>, response: Response<ResponseMateri>) {
                if (response.isSuccessful) {
                    val responseMateri = response.body()
                    val dataMateri = responseMateri?.data
                    val materiAdapter = MateriAdapter(dataMateri ?: emptyList())
                    materi.layoutManager = LinearLayoutManager(requireContext())
                    materi.setHasFixedSize(true)
                    materi.adapter = materiAdapter
                }
            }

            override fun onFailure(call: Call<ResponseMateri>, t: Throwable) {
                Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }
}