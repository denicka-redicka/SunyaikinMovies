package com.example.feauture_movies_impl.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.core.Movie
import com.example.core.MovieDetails
import com.example.core_repository_api.di.CoreRepositoryApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MoviesViewModel(
    repositoryApi: CoreRepositoryApi<
            Flow<PagingData<Movie>>,
            MovieDetails>
) : ViewModel() {

    private val repository = repositoryApi.getMoviesRepository()
    val moviesState: StateFlow<PagingData<Movie>> =
        repository.getMoviesList().stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())


    class MoviesVmFactory @Inject constructor(
        private val repositoryApi: CoreRepositoryApi<Flow<@JvmSuppressWildcards PagingData<@JvmSuppressWildcards Movie>>, @JvmSuppressWildcards MovieDetails>
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MoviesViewModel(
                repositoryApi
            ) as T
        }
    }
}