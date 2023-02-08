package com.example.sunyaikinmovies

import android.app.Application
import android.content.Context
import androidx.paging.PagingData
import com.example.core.data.Movie
import com.example.core.data.MovieDetails
import com.example.core.repository.impl.di.RepositoryComponent
import com.example.core.repository.impl.di.RepositoryDependencies
import com.example.core_repository_api.di.CoreRepositoryApi
import com.example.favorites.di.FavoritesFeatureDependencies
import com.example.details.di.MovieDetailsFeatureDependencies
import com.example.favorites.di.FavoritesFeatureDependenciesProvider
import com.example.details.di.MovieDetailsFeatureDependenciesProvider
import com.example.feauture_movies_impl.di.MoviesFeatureDependencies
import com.example.feauture_movies_impl.di.MoviesFeatureDependenciesProvider
import com.example.impl.di.CoreNetworkApi
import com.example.impl.di.DatabaseComponent
import com.example.impl.di.NetworkComponent
import com.example.sunyaikinmovies.di.AppComponent
import com.example.sunyaikinmovies.di.AppInjector
import com.example.sunyaikinmovies.di.DaggerAppComponent
import kotlinx.coroutines.flow.Flow

class MoviesApp : Application(), AppInjector, MoviesFeatureDependenciesProvider,
    FavoritesFeatureDependenciesProvider, MovieDetailsFeatureDependenciesProvider {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appInjector(this)
            .build()
    }

    override val dependencies: MoviesFeatureDependencies = appComponent
    override val detailsDependencies: MovieDetailsFeatureDependencies = appComponent
    override val favoritesDependencies: FavoritesFeatureDependencies = appComponent

    override fun movieRepository(): CoreRepositoryApi<Flow<PagingData<Movie>>, MovieDetails> =
        RepositoryComponent.initAndGet(
            object : RepositoryDependencies {
                override fun network(): CoreNetworkApi<List<Movie>, MovieDetails> =
                    NetworkComponent.initAndGet()

                override fun dao(): com.example.impl.di.CoreLocalApi<MovieDetails> =
                    DatabaseComponent.initAndGet(this@MoviesApp)
            }
        )
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MoviesApp ->  appComponent
        else -> this.applicationContext.appComponent
    }
