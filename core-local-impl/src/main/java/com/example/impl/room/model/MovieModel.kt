package com.example.impl.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.impl.data.MOVIES_TABLE_NAME

@Entity (tableName = MOVIES_TABLE_NAME)
data class MovieModel(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "movie_name")
    val movieName: String,
    @ColumnInfo(name = "movie_poster")
    val posterUrl: String,
    @ColumnInfo(name = "movie_preview")
    val posterPreviewUrl: String,
    @ColumnInfo(name = "movie_genres")
    val genres: String,
    @ColumnInfo(name = "movie_description")
    val description: String,
    @ColumnInfo(name = "movie_countries")
    val countries: String,
    @ColumnInfo(name = "movie_year")
    val year: String
)
