package com.example.details.view

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.example.core.data.Movie
import com.example.core.data.MovieDetails
import com.example.core_repository_api.di.CoreRepositoryApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel(
    repositoryApi: CoreRepositoryApi<
            Flow<PagingData<Movie>>,
            MovieDetails>
) : ViewModel() {

    private val repository = repositoryApi.getMoviesRepository()

    fun getMovieDetails(id: Int) {
        if (id != -1) {
            viewModelScope.launch(Dispatchers.IO) {
                _detailsLiveData.value = repository.getMovieInfo(id)
            }
        }
    }

    private val _detailsLiveData = MutableStateFlow<MovieDetails?>(null)
    val detailsLiveData: StateFlow<MovieDetails?> = _detailsLiveData


    @Suppress("UNCHECKED_CAST")
    class MoviesVmFactory @Inject constructor(
        private val repositoryApi: CoreRepositoryApi<Flow<@JvmSuppressWildcards PagingData<@JvmSuppressWildcards Movie>>,
                @JvmSuppressWildcards MovieDetails>
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MovieDetailsViewModel(
                repositoryApi
            ) as T
        }
    }
}