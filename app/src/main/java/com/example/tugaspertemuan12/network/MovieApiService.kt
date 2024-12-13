package com.example.tugaspertemuan12.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object MovieApiService {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}