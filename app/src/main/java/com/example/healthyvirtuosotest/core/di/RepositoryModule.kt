package com.example.healthyvirtuosotest.core.di

import com.example.healthyvirtuosotest.arch.movies.domain.mappers.MoviesMappers
import com.example.healthyvirtuosotest.arch.movies.domain.usecases.GetPopularMovies
import com.example.healthyvirtuosotest.arch.movies.domain.usecases.MoviesUseCase
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
    fun provideMovieMapper() = MoviesMappers()

    @Provides
    fun provideGetPopularMovies(moviesRepository: MoviesRepository) = GetPopularMovies(moviesRepository)
    @Provides
    fun moviesUseCase(getPopularMovies: GetPopularMovies) = MoviesUseCase(getPopularMovies)

    @Provides
    @Singleton
    fun provideRepositoryMovies(
        moviesRemoteDataSource: MoviesRemoteDataSource,
        moviesMappers: MoviesMappers
    ): MoviesRepository {
        return MoviesRepository(moviesRemoteDataSource, moviesMappers)
    }
}