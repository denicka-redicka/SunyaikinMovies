package com.example.impl.di

import com.example.core.Movie
import com.example.core.MovieDetails
import com.example.impl.di.CoreNetworkApi
import dagger.Component

@Component(modules = [NetworkModule::class])
interface NetworkComponent :
    CoreNetworkApi<List<@JvmSuppressWildcards Movie>, @JvmSuppressWildcards MovieDetails> {

    companion object {

        fun initAndGet(): NetworkComponent =
            DaggerNetworkComponent.builder().build()

    }
}