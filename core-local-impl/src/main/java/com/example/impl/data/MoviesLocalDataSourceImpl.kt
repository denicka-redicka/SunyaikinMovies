package com.example.impl.data

import com.example.core.data.MovieDetails
import com.example.impl.room.MoviesDao
import javax.inject.Inject

class MoviesLocalDataSourceImpl @Inject constructor(
    private val dao: MoviesDao
) : MoviesLocalDataSource<@JvmSuppressWildcards MovieDetails> {

    override suspend fun getFavoritesList(): List<MovieDetails> =
        dao.getAll().map { it.toMovieDetails() }


    override suspend fun saveMovie(movie: MovieDetails) {
        dao.insert(movie.toMovieModel())
    }

    override suspend fun removeMovie(id: Int) {
        dao.removeMovie(id)
    }

    override suspend fun getMoviesDetails(id: Int): MovieDetails? =
        dao.getMovie(id)?.toMovieDetails()

    override suspend fun getSavedIds(): List<Int> = dao.getIds()
}