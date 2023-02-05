package com.example.sunyaikinmovies.di

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.core.Movie
import com.example.core.MovieDetails
import com.example.core.repository.impl.di.RepositoryDependencies
import com.example.core_repository_api.di.CoreRepositoryApi
import com.example.feauture_movies_impl.di.MoviesFeatureDependencies
import com.example.impl.di.CoreNetworkApi
import kotlinx.coroutines.flow.Flow

interface AppInjector {

    fun movieRepository(): CoreRepositoryApi<Flow<PagingData<@JvmSuppressWildcards Movie>>, @JvmSuppressWildcards MovieDetails>
}