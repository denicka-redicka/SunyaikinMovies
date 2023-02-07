package com.example.feauture_movies_impl.di

import com.example.core.di.Feature
import com.example.feauture_movies_impl.view.MoviesFragment
import dagger.Component

@[Feature Component(dependencies = [MoviesFeatureDependencies::class])]
interface MoviesFeatureComponent {

    fun inject(fragment: MoviesFragment)

    companion object {
        fun initAndGet(dependencies: MoviesFeatureDependencies): MoviesFeatureComponent =
            DaggerMoviesFeatureComponent.builder()
                .moviesFeatureDependencies(dependencies)
                .build()
    }
}