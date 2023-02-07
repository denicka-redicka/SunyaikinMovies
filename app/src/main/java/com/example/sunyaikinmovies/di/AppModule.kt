package com.example.sunyaikinmovies.di

import com.example.core.navigarion.Router
import com.example.core.navigarion.RouterImpl
import dagger.Module
import dagger.Provides

@Module(includes = [BindsModule::class])
class AppModule {
}

@Module
class BindsModule {

    @Provides
    fun provideRouter(): Router {
        return RouterImpl
    }
}