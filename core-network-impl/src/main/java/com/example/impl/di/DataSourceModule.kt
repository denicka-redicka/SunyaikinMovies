package com.example.impl.di

import com.example.core.data.Movie
import com.example.core.data.MovieDetails
import com.example.impl.data.MoviesRemoteDataSourceApi
import com.example.impl.data.MoviesRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface DataSourceModule {

    @Binds
    fun bindRemoteDataSource(implementation: MoviesRemoteDataSourceImpl):
            MoviesRemoteDataSourceApi<List<@JvmSuppressWildcards Movie>, @JvmSuppressWildcards MovieDetails>
}