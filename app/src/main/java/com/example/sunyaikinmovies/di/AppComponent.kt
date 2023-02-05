package com.example.sunyaikinmovies.di

import com.example.core.Movie
import com.example.core.MovieDetails
import com.example.core.repository.impl.di.RepositoryComponent
import com.example.core.repository.impl.di.RepositoryDependencies
import com.example.core_repository_api.data.MoviesRepository
import com.example.core_repository_api.di.CoreRepositoryApi
import com.example.feature_movie_details.di.MovieDetailsFeatureDependencies
import com.example.feauture_movies_impl.di.MoviesFeatureDependencies
import com.example.feauture_movies_impl.di.MoviesFeatureDependenciesProvider
import com.example.impl.di.CoreNetworkApi
import com.example.impl.di.NetworkComponent
import com.example.sunyaikinmovies.MainActivity
import dagger.Component
import javax.inject.Singleton

@[Singleton Component( dependencies = [AppInjector::class])]
interface AppComponent: MoviesFeatureDependencies, MovieDetailsFeatureDependencies {

    fun inject(activity: MainActivity)

    companion object {
        fun initAndGet(appInjector: AppInjector) =
            DaggerAppComponent.builder()
                .appInjector(appInjector)
                .build()
    }
}