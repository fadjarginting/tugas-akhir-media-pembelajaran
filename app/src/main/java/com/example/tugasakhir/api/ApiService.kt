package com.example.tugasakhir.api

import com.example.tugasakhir.model.ResponseLogin
import com.example.tugasakhir.model.ResponseMateri
import com.example.tugasakhir.model.ResponseRegister
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("materi")
    fun getMateri(): Call<ResponseMateri>
    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseRegister>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseLogin>

}