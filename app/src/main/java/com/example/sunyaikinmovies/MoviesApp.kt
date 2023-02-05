package com.example.sunyaikinmovies

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.core.Movie
import com.example.core.MovieDetails
import com.example.core.repository.impl.di.RepositoryComponent
import com.example.core.repository.impl.di.RepositoryDependencies
import com.example.core_repository_api.di.CoreRepositoryApi
import com.example.feature_movie_details.di.MovieDetailsFeatureDependencies
import com.example.feature_movie_details.di.MovieDetailsFeatureDependenciesProvider
import com.example.feauture_movies_impl.di.MoviesFeatureDependencies
import com.example.feauture_movies_impl.di.MoviesFeatureDependenciesProvider
import com.example.impl.di.CoreNetworkApi
import com.example.impl.di.NetworkComponent
import com.example.sunyaikinmovies.di.AppComponent
import com.example.sunyaikinmovies.di.AppInjector
import com.example.sunyaikinmovies.di.DaggerAppComponent
import kotlinx.coroutines.flow.Flow

class MoviesApp: Application(), AppInjector, MoviesFeatureDependenciesProvider,
    MovieDetailsFeatureDependenciesProvider {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appInjector(this)
            .build()
    }

    override val dependencies: MoviesFeatureDependencies = appComponent
    override val detailsDependencies: MovieDetailsFeatureDependencies = appComponent
    override fun movieRepository(): CoreRepositoryApi<Flow<PagingData<Movie>>, MovieDetails> =
        RepositoryComponent.initAndGet(
            object: RepositoryDependencies {
                override fun network(): CoreNetworkApi<List<Movie>, MovieDetails> = NetworkComponent.initAndGet()

            }
        )


}

