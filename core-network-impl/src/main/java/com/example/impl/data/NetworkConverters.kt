package com.example.impl.data

import com.example.core.data.Movie
import com.example.core.data.MovieDetails
import com.example.impl.models.MovieDetailsResponse
import com.example.impl.models.MoviesResponse

internal fun MoviesResponse.toMoviesList(): List<Movie> {
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

internal fun MovieDetailsResponse.toMovieDetail(): MovieDetails {
    var genresString = ""
    var countriesString = ""

    genres?.forEachIndexed { index, item ->
        genresString += if (index != genres.size - 1)
            "${item.genre}, "
        else
            item.genre
    }

    countries?.forEachIndexed { index, item ->
        countriesString += if (index != countries.size - 1)
            "${item.country}, "
        else
            item.country
    }
    return MovieDetails(
        id = id ?: -1,
        name = nameRu ?: "",
        posterUrl = posterUrl ?: "",
        posterUrlPreview = posterUrlPreview?: "",
        genres = genresString,
        description = description ?: "",
        countries = countriesString,
        year = "$year"
        )
}