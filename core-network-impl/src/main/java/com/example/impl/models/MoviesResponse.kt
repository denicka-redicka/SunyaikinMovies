package com.example.impl.models

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse (
    val pageCount: Int? = null,
    val films: List<MovieResponse>?
)

@Serializable
data class MovieResponse(
    val filmId: Int?,
    val nameRu: String?,
    val year: String?,
    val nameEn:	String?,
    val ratingVoteCount:Int?,
    val posterUrlPreview: String?
)