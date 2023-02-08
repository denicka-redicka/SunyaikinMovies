package com.example.feauture_movies_impl.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.data.Movie
import com.example.core.data.MovieDetails
import com.example.core_repository_api.di.CoreRepositoryApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel(
    repositoryApi: CoreRepositoryApi<
            Flow<PagingData<Movie>>,
            MovieDetails>
) : ViewModel() {

    private val repository = repositoryApi.getMoviesRepository()
    val moviesState: StateFlow<PagingData<Movie>> =
        repository.getMoviesList().cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun handleOnStarClicked(id: Int, isFavorite: Boolean): Boolean {
        viewModelScope.launch(Dispatchers.IO) {
            if (isFavorite)
                repository.saveMovie(id)
            else
                repository.removeMovie(id)

            Log.d("DEBUG", "Operation completed")
        }
        return true
    }


    @Suppress("UNCHECKED_CAST")
    class MoviesVmFactory @Inject constructor(
        private val repositoryApi: CoreRepositoryApi<Flow<@JvmSuppressWildcards PagingData<@JvmSuppressWildcards Movie>>,
                @JvmSuppressWildcards MovieDetails>
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MoviesViewModel(
                repositoryApi
            ) as T
        }
    }
}