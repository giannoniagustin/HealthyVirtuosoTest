package com.example.healthyvirtuosotest.core.di

import com.example.healthyvirtuosotest.arch.movies.domain.mappers.MoviesMappers
import com.example.healthyvirtuosotest.arch.movies.repository.MoviesRepository
import com.example.healthyvirtuosotest.arch.movies.repository.remote.MoviesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepositoryMovies(
        moviesRemoteDataSource: MoviesRemoteDataSource,
        moviesMappers: MoviesMappers,
    ): MoviesRepository {
        return MoviesRepository(moviesRemoteDataSource, moviesMappers)
    }
}