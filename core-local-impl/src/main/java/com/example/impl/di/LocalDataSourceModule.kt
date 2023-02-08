package com.example.impl.di

import com.example.impl.data.MoviesLocalDataSource
import com.example.core.data.MovieDetails
import com.example.impl.data.MoviesLocalDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface LocalDataSourceModule {

    @Binds
    fun provideLocalDataSource(implementation: MoviesLocalDataSourceImpl):
            MoviesLocalDataSource<@JvmSuppressWildcards MovieDetails>
}