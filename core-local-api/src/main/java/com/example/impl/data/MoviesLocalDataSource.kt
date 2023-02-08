package com.example.impl.data

interface MoviesLocalDataSource<Favorite> {

    fun getFavoritesList(): List<Favorite>

    fun getMoviesDetails(id: Int): Favorite

    fun saveMovie(movie: Favorite)

    fun removeMovie(id: Int)
}