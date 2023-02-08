package com.example.impl.data

import com.example.core.data.MovieDetails
import com.example.impl.room.model.MovieModel

internal fun MovieModel.toMovieDetails(): MovieDetails {
    return MovieDetails(
        id = movieId,
        name = movieName,
        posterUrlPreview = posterPreviewUrl,
        posterUrl = posterUrl,
        year = year,
        genres = genres,
        countries = countries,
        description = description
    )
}


internal fun MovieDetails.toMovieModel(): MovieModel {
    return MovieModel(
        movieId = id,
        movieName = name,
        posterPreviewUrl = posterUrlPreview,
        posterUrl = posterUrl,
        year = year,
        genres = genres,
        countries = countries,
        description = description
    )
}