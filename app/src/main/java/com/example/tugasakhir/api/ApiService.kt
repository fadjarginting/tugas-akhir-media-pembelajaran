package com.example.tugasakhir.api

import com.example.tugasakhir.model.ResponseMateri
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiMateriService {
    @GET("materi")
    fun getMateri(): Call<ResponseMateri>

    @POST("register")
    fun register(
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseMateri>
}