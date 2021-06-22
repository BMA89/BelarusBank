package com.example.belarusbankinfo

import okhttp3.OkHttpClient
import org.w3c.dom.Comment
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

val BASE_URL = "https://belarusbank.by/api/"

interface ApiService {
    @GET("kursExchange?city=Минск")
    fun getInfo() : Call<List<Bank>>

    companion object Factory{
        fun create(): ApiService{
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ApiService :: class.java)
        }

    }
}
