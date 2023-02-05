package com.example.impl.data

import com.example.core.MovieDetails
import com.example.impl.models.MovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("films/top?type=TOP_100_POPULAR_FILMS")
    suspend fun getTopList(@Query("page") page: Int): MoviesResponse

    @GET("films/{id}")
    suspend fun getMoviesInfo(@Path("id") id: Int): MovieDetailsResponse
}