package com.example.core.repository.impl.di

import androidx.paging.PagingData
import com.example.core.data.Movie
import com.example.core.data.MovieDetails
import com.example.core_repository_api.di.CoreRepositoryApi
import dagger.Component
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@[Singleton Component(modules = [BindsModule::class], dependencies = [RepositoryDependencies::class])]
interface RepositoryComponent :
    CoreRepositoryApi<Flow<@JvmSuppressWildcards PagingData<@JvmSuppressWildcards Movie>>, @JvmSuppressWildcards MovieDetails> {

        companion object {
            fun initAndGet(dependencies: RepositoryDependencies): RepositoryComponent =
                DaggerRepositoryComponent.builder()
                    .repositoryDependencies(dependencies)
                    .build()
        }
}