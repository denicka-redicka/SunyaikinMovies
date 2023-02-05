package com.example.impl.data

import com.example.core.Movie
import com.example.core.MovieDetails
import com.example.core.NamedValue
import com.example.impl.models.MovieDetailsResponse

fun MoviesResponse.toMoviesList(): List<Movie> {
    val list = mutableListOf<Movie>()
    films?.forEach { film ->
        list += Movie(
            movieId = film.filmId ?: -1,
            name = film.nameRu ?: "",
            year = film.year ?: "",
            posterUrlPreview = film.posterUrlPreview ?: "",
            isFavorite = false
        )
    }
    return list
}

fun MovieDetailsResponse.toMovieDetail(): MovieDetails {
    return MovieDetails(
        id = id ?: -1,
        name = nameRu ?: "",
        posterUrl = posterUrl ?: "",
        genres = genres?.map {
            NamedValue(it.genre ?: "")
        } ?: emptyList(),
        description = description ?: "",
        countries = countries?.map {
            NamedValue(it.country ?: "")
        } ?: emptyList(),

        )
}