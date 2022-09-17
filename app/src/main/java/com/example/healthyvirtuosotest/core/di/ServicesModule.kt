package com.example.healthyvirtuosotest.core.di

import com.example.healthyvirtuosotest.arch.movies.repository.service.MoviesServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Provides
    @Singleton
    fun moviesServices(retrofit: Retrofit): MoviesServices {
        return retrofit.create(MoviesServices::class.java)
    }
}