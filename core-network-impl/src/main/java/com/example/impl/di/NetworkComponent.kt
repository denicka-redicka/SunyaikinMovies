package com.example.impl.di

import com.example.core.data.Movie
import com.example.core.data.MovieDetails
import dagger.Component

@Component(modules = [NetworkModule::class])
interface NetworkComponent :
    CoreNetworkApi<List<@JvmSuppressWildcards Movie>, @JvmSuppressWildcards MovieDetails> {

    companion object {

        fun initAndGet(): NetworkComponent =
            DaggerNetworkComponent.builder().build()

    }
}