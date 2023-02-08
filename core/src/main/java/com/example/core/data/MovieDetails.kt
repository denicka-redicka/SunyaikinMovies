package com.example.core.data

data class MovieDetails(
    val id: Int,
    val name: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val genres: String,
    val description: String,
    val countries: String,
    val year: String
) {
    fun toSavedMovie(): Movie {
        return Movie(
            movieId = id,
            name = name,
            year = year,
            posterUrlPreview = posterUrlPreview,
            isFavorite = true
        )
    }
}
