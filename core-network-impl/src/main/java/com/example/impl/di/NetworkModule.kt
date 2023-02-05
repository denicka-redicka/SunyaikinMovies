package com.example.impl.di

import com.example.impl.data.MoviesService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit


private val apiKey = "19da1bbd-22cd-49d4-90b1-bc39126b6de1"
private val baseUrl = "https://kinopoiskapiunofficial.tech/api/v2.2/"

@dagger.Module(includes = [DataSourceModule::class])
class NetworkModule {

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    private val contentType = "application/json".toMediaType()

    @Provides
    internal fun MoviesService(): MoviesService {
        val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(Interceptor { chain ->
                val authorizedRequest = chain.request().newBuilder()
                    .addHeader("X-API-KEY", apiKey)
                    .build()
                chain.proceed(authorizedRequest)
            })
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(client)
            .build()
        return retrofit.create()
    }
}