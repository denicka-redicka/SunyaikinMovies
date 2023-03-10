package com.example.core.repository.impl.di

import com.example.impl.di.CoreLocalApi
import com.example.core.data.Movie
import com.example.core.data.MovieDetails
import com.example.impl.di.CoreNetworkApi

interface RepositoryDependencies {

    fun network(): CoreNetworkApi<List<@JvmSuppressWildcards Movie>, MovieDetails>

    fun dao(): CoreLocalApi<@JvmSuppressWildcards MovieDetails>
}