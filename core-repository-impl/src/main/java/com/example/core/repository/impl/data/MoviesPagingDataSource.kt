package com.example.core.repository.impl.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.Movie
import com.example.core.MovieDetails
import com.example.impl.di.CoreNetworkApi

class MoviesPagingDataSource(
    private val api: CoreNetworkApi<List<Movie>, MovieDetails>

) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return INITIAL_VALUE
        val page = state.closestPageToPosition(anchorPosition) ?: return INITIAL_VALUE
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1) ?: INITIAL_VALUE
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val pageNumber = params.key ?: INITIAL_VALUE
        return try {
            val response = api.moviesRemoteService().getMoviesList(pageNumber)
            val nextPageNumber: Int? = if (pageNumber == 1) null else (pageNumber - 1)
            val prevPageNumber: Int? = pageNumber + 1

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