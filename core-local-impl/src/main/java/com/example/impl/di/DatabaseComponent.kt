package com.example.impl.di

import android.app.Application
import com.example.core.data.MovieDetails
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@[Singleton Component(modules = [DatabaseModule::class])]
interface DatabaseComponent: CoreLocalApi<@JvmSuppressWildcards MovieDetails> {

    companion object {
        fun initAndGet(app: Application): DatabaseComponent =
            DaggerDatabaseComponent.builder()
                .application(app)
                .build()
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): DatabaseComponent
    }
}