package com.example.core.repository.impl.di

import androidx.paging.PagingData
import com.example.core.data.Movie
import com.example.core.data.MovieDetails
import com.example.core.repository.impl.data.MoviesRepositoryImpl
import com.example.core_repository_api.data.MoviesRepository
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.flow.Flow

@Module
interface BindsModule {

    @Binds
    fun provideMoviesRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository<
            Flow<@JvmSuppressWildcards PagingData<@JvmSuppressWildcards Movie>>,
            @JvmSuppressWildcards MovieDetails>

}