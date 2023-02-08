package com.example.favorites.di

import com.example.core.di.Feature
import com.example.favorites.view.FavoritesFragment
import dagger.Component

@[Feature Component(dependencies = [FavoritesFeatureDependencies::class])]
interface FavoritesFeatureComponent {

    fun inject(fragment: FavoritesFragment)

    companion object {
        fun initAndGet(dependencies: FavoritesFeatureDependencies): FavoritesFeatureComponent =
            DaggerFavoritesFeatureComponent.builder()
                .favoritesFeatureDependencies(dependencies)
                .build()
    }
}