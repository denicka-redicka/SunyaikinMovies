package com.example.feature_movie_details.view


import androidx.lifecycle.*
import androidx.paging.PagingData
import com.example.core.data.Movie
import com.example.core.data.MovieDetails
import com.example.core_repository_api.di.CoreRepositoryApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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
                _detailsLiveData.postValue(repository.getMovieInfo(id))
            }
        }
    }

    private val _detailsLiveData = MutableLiveData<MovieDetails>()
    val detailsLiveData: LiveData<MovieDetails> = _detailsLiveData


    class MoviesVmFactory @Inject constructor(
        private val repositoryApi: CoreRepositoryApi<Flow<@JvmSuppressWildcards PagingData<@JvmSuppressWildcards Movie>>, @JvmSuppressWildcards MovieDetails>
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MovieDetailsViewModel(
                repositoryApi
            ) as T
        }
    }
}