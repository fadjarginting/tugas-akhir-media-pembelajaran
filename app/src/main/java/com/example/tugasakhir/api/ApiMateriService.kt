package com.example.tugasakhir.api

import com.example.tugasakhir.ResponseMateri
import retrofit2.Call
import retrofit2.http.GET

interface ApiMateriService {
    @GET("materi")
    fun getMateri () : Call<ResponseMateri>
}