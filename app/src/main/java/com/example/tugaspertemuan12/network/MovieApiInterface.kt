package com.example.tugaspertemuan12.network

import com.example.tugaspertemuan12.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterface {
    @GET("movie/popular")
    fun getMovieList(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>
}