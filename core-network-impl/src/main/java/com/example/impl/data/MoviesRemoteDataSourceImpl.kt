package com.example.impl.data

import com.example.core.data.Movie
import com.example.core.data.MovieDetails
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val api: MoviesService
): MoviesRemoteDataSourceApi<List<@JvmSuppressWildcards Movie>, @JvmSuppressWildcards MovieDetails> {

    override suspend fun getMoviesList(page: Int): List<Movie> {
        val response = api.getTopList(page)
        return response.toMoviesList()
    }

    override suspend fun getMoviesInfo(id: Int): MovieDetails {
        return api.getMoviesInfo(id).toMovieDetail()
    }
}