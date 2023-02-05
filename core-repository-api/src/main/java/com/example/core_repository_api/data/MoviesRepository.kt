package com.example.core_repository_api.data

interface MoviesRepository <Movies, MoviesInfo> {

    fun getMoviesList(): Movies

    suspend fun getMovieInfo(id: Int): MoviesInfo

    suspend fun getFavoritesList(): Movies

    suspend fun saveMovie(id: Int)
}