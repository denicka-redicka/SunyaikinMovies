package com.example.core_repository_api.data

interface MoviesRepository <Movies, MoviesInfo> {

    fun getMoviesList(): Movies

    suspend fun getMovieInfo(id: Int): MoviesInfo

    suspend fun getFavoritesList(): List<MoviesInfo>

    suspend fun saveMovie(id: Int)

    fun removeMovie(id: Int)
}