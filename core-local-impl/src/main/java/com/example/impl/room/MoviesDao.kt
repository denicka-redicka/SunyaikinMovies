package com.example.impl.room

import androidx.room.*
import com.example.impl.room.model.MovieModel

@Dao
interface MoviesDao {

    @Query("SELECT * from movies")
    fun getAll(): List<MovieModel>

    @Query("SELECT * from movies WHERE movie_id =:id")
    fun getMovie(id: Int): MovieModel?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(movie: MovieModel): Long

    @Query("DELETE FROM movies WHERE movie_id = :id")
    fun removeMovie(id: Int)

    @Query("SELECT movie_id from movies")
    fun getIds(): List<Int>
}