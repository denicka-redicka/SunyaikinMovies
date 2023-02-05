package com.example.impl.data


interface MoviesRemoteDataSourceApi<Movies, MoviesInfo> {

    suspend fun getMoviesList(page: Int): Movies

    suspend fun getMoviesInfo(id: Int): MoviesInfo
}