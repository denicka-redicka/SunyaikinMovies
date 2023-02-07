package com.example.impl.data

import com.example.core.data.Movie
import com.example.core.data.MovieDetails
import com.example.core.data.NamedValue
import com.example.impl.models.MovieDetailsResponse
import com.example.impl.models.MoviesResponse

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