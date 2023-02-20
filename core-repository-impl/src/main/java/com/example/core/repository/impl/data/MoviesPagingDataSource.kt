package com.example.core.repository.impl.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.data.Movie
import com.example.core.data.MovieDetails
import com.example.impl.data.MoviesLocalDataSource
import com.example.impl.data.MoviesRemoteDataSourceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesPagingDataSource(
    private val api: MoviesRemoteDataSourceApi<List<Movie>, MovieDetails>,
    private val dao: MoviesLocalDataSource<MovieDetails>

) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int {
        val anchorPosition = state.anchorPosition ?: return INITIAL_VALUE
        val page = state.closestPageToPosition(anchorPosition) ?: return INITIAL_VALUE
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1) ?: INITIAL_VALUE
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val pageNumber = params.key ?: INITIAL_VALUE
        return try {
            val response = api.getMoviesList(pageNumber)
            val savedFilmsIds = withContext(Dispatchers.IO){
                dao.getSavedIds()
            }
            response.forEach { movie ->
                movie.isFavorite = savedFilmsIds.contains(movie.movieId)
            }
            val prevPageNumber: Int? = if (pageNumber == 1) null else (pageNumber - 1)
            val nextPageNumber: Int? = pageNumber + 1

            Log.d("DEBUG", response.toString())
            LoadResult.Page(
                data = response,
                prevKey = prevPageNumber,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val INITIAL_VALUE = 1
        const val MAX_PAGE_SIZE = 20
    }
}