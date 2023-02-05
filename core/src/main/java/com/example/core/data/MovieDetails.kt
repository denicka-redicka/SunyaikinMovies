package com.example.core

data class MovieDetails(
    val id: Int,
    val name: String,
    val posterUrl: String,
    val genres: List<NamedValue>,
    val description: String,
    val countries: List<NamedValue>,
)

data class NamedValue(
    val name: String
)
