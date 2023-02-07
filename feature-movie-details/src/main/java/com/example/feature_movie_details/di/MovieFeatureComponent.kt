package com.example.feature_movie_details.di

import com.example.core.di.Feature
import com.example.feature_movie_details.view.MovieDetailsFragment
import dagger.Component

@[Feature Component(dependencies = [MovieDetailsFeatureDependencies::class])]
interface MovieDetailsFeatureComponent {

    fun inject(fragment: MovieDetailsFragment)

    companion object {
        fun initAndGet(dependencies: MovieDetailsFeatureDependencies): MovieDetailsFeatureComponent =
            DaggerMovieDetailsFeatureComponent.builder()
                .movieDetailsFeatureDependencies(dependencies)
                .build()
    }
}