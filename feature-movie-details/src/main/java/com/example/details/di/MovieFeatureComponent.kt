package com.example.details.di

import com.example.core.di.Feature
import com.example.details.view.MovieDetailsFragment
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