package com.example.favorites.view

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

class FavoritesViewModel(
    repositoryApi: CoreRepositoryApi<
            Flow<PagingData<Movie>>,
            MovieDetails>
) : ViewModel() {

    private val repository = repositoryApi.getMoviesRepository()

    fun removeFromFavoritesList(id: Int, isFavorites: Boolean = true): Boolean {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeMovie(id)
        }
        return true
    }

    fun getAllFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getFavoritesList()
            _detailsLiveData.value = response.map { it.toSavedMovie() }
        }
    }

    private val _detailsLiveData = MutableStateFlow<List<Movie>?>(null)
    val detailsLiveData: StateFlow<List<Movie>?> = _detailsLiveData


    @Suppress("UNCHECKED_CAST")
    class MoviesVmFactory @Inject constructor(
        private val repositoryApi: CoreRepositoryApi<Flow<@JvmSuppressWildcards PagingData<@JvmSuppressWildcards Movie>>,
                @JvmSuppressWildcards MovieDetails>
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FavoritesViewModel(
                repositoryApi
            ) as T
        }
    }
}