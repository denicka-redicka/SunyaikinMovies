package com.example.core

data class Movie(
    val movieId: Int,
    val name: String,
    val year: String,
    val posterUrlPreview: String,
    val isFavorite: Boolean
)

data class MoviesWithPages(
    val page: Int,
    val movies: List<Movie>
)
