package com.example.feauture_movies_impl.di

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.core.Movie
import com.example.core.MovieDetails
import com.example.core_repository_api.di.CoreRepositoryApi
import kotlinx.coroutines.flow.Flow


interface MoviesFeatureDependencies {

    fun movieRepository(): CoreRepositoryApi<
            Flow<@JvmSuppressWildcards PagingData<@JvmSuppressWildcards Movie>>,
            @JvmSuppressWildcards MovieDetails>
}