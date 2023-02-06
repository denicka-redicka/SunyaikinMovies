package com.example.core.repository.impl.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.core.Movie
import com.example.core.MovieDetails
import com.example.core.repository.impl.data.MoviesPagingDataSource.Companion.MAX_PAGE_SIZE
import com.example.core_repository_api.data.MoviesRepository
import com.example.impl.di.CoreNetworkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MoviesRepositoryImpl @Inject constructor(
    private val networkApi: CoreNetworkApi<
            List<@JvmSuppressWildcards Movie>,
            @JvmSuppressWildcards MovieDetails
            >
) : MoviesRepository<Flow<@JvmSuppressWildcards PagingData<@JvmSuppressWildcards Movie>>, @JvmSuppressWildcards MovieDetails> {


    override fun getMoviesList(): Flow<PagingData<Movie>> {
        val pager = Pager(
            config = PagingConfig(5, enablePlaceholders = true),
            pagingSourceFactory = {
                MoviesPagingDataSource(
                    networkApi
                )
            })
        return pager.flow
    }

    override suspend fun getMovieInfo(id: Int): MovieDetails = withContext(Dispatchers.IO) {
        return@withContext networkApi.moviesRemoteService().getMoviesInfo(id)
    }

    override suspend fun getFavoritesList(): Flow<PagingData<Movie>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveMovie(id: Int) {
        TODO("Not yet implemented")
    }
}