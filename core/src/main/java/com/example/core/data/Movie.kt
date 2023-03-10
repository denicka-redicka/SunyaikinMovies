package com.example.core.data

data class Movie(
    val movieId: Int,
    val name: String,
    val year: String,
    val posterUrlPreview: String,
    var isFavorite: Boolean
)
