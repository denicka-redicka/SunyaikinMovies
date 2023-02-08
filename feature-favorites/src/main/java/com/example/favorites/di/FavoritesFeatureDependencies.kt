package com.example.favorites.di

import androidx.paging.PagingData
import com.example.core.data.Movie
import com.example.core.data.MovieDetails
import com.example.core.navigarion.Router
import com.example.core_repository_api.di.CoreRepositoryApi
import kotlinx.coroutines.flow.Flow

interface FavoritesFeatureDependencies {

    fun movieRepository(): CoreRepositoryApi<
            Flow<@JvmSuppressWildcards PagingData<@JvmSuppressWildcards Movie>>,
            @JvmSuppressWildcards MovieDetails>

    fun router(): Router
}