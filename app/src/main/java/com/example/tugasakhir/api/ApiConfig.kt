package com.example.tugasakhir.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private const val baseURL = "http://192.168.1.5/androidapi/public/api/"
    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getService () : ApiService{
        return getRetrofit().create(ApiService::class.java)
    }
}