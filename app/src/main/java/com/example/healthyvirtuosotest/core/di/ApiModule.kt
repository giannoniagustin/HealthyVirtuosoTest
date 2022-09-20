package com.example.healthyvirtuosotest.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    @Named("ApiKey")
    fun providerApiKey() = "4113f3ad734e747a5b463cde8c55de42"

    @Singleton
    @Provides
    fun providesHttpInterceptor(@Named("ApiKey") apiKey: String) = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("api_key", apiKey)
            .build()
        val response = chain.proceed(request)
        response

    }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpInterceptor: Interceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()


}