package com.example.sunyaikinmovies.di

import androidx.paging.PagingData
import com.example.core.data.Movie
import com.example.core.data.MovieDetails
import com.example.core_repository_api.di.CoreRepositoryApi
import kotlinx.coroutines.flow.Flow

interface AppInjector {

    fun movieRepository(): CoreRepositoryApi<Flow<PagingData<Movie>>, MovieDetails>
}