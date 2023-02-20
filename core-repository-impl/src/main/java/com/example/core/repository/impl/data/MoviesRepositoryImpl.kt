package com.example.core.repository.impl.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.Movie
import com.example.core.data.MovieDetails
import com.example.core_repository_api.data.MoviesRepository
import com.example.impl.di.CoreLocalApi
import com.example.impl.di.CoreNetworkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MoviesRepositoryImpl @Inject constructor(
    private val networkApi: CoreNetworkApi<
            List<@JvmSuppressWildcards Movie>,
            @JvmSuppressWildcards MovieDetails
            >,
    daoApi: CoreLocalApi<@JvmSuppressWildcards MovieDetails>
) : MoviesRepository<Flow<@JvmSuppressWildcards PagingData<@JvmSuppressWildcards Movie>>, @JvmSuppressWildcards MovieDetails> {

    private val dao = daoApi.getLocalDataSource()

    override fun getMoviesList(): Flow<PagingData<Movie>> {
        val pager = Pager(
            config = PagingConfig(5, enablePlaceholders = true),
            pagingSourceFactory = {
                MoviesPagingDataSource(
                    networkApi.moviesRemoteService(),
                    dao
                )
            })
        return pager.flow
    }

    override suspend fun getMovieInfo(id: Int): MovieDetails = withContext(Dispatchers.IO) {
        return@withContext dao.getMoviesDetails(id) ?:
        networkApi.moviesRemoteService().getMoviesInfo(id)
    }

    override suspend fun getFavoritesList(): List<MovieDetails> {
        return dao.getFavoritesList()
    }

    override suspend fun saveMovie(id: Int) {
        val movie = getMovieInfo(id)
        dao.saveMovie(movie)
    }

    override suspend fun removeMovie(id: Int) {
        dao.removeMovie(id)
    }
}