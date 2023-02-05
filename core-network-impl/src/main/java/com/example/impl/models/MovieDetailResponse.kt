package com.example.impl.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsResponse(
    @SerialName("kinopoiskId")
    val id: Int?,
    @SerialName("nameRu")
    val nameRu: String?,
    @SerialName("posterUrl")
    val posterUrl: String?,
    @SerialName("genres")
    val genres: List<Genre>?,
    @SerialName("description")
    val description: String?,
    @SerialName("countries")
    val countries: List<Country>?,
)

@Serializable
data class Genre(
    val genre: String?
)
@Serializable
data class Country(
    val country: String?
)
