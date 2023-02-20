package com.example.impl.data

interface MoviesLocalDataSource<Favorite> {

    suspend fun getFavoritesList(): List<Favorite>

    suspend fun getMoviesDetails(id: Int): Favorite?

    suspend fun saveMovie(movie: Favorite)

    suspend fun removeMovie(id: Int)

    suspend fun getSavedIds(): List<Int>
}