package com.example.tugasakhir.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
object ApiMateriConfig {
    private const val baseURL = "http://192.168.1.2/androidapi/public/api/"
    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getService () : ApiMateriService{
        return getRetrofit().create(ApiMateriService::class.java)
    }
}