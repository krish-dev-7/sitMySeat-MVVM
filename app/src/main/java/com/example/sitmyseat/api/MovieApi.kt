package com.example.sitmyseat.api

import com.example.sitmyseat.models.MovieResponse
import com.example.sitmyseat.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("3/list/1")
    suspend fun getMovies(
        @Query("api_key")
        key: String =API_KEY
    ): Response<MovieResponse>

}