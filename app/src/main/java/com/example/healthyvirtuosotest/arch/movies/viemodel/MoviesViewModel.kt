package com.example.healthyvirtuosotest.arch.movies.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.healthyvirtuosotest.arch.movies.domain.model.Movie
import com.example.healthyvirtuosotest.arch.movies.domain.usecases.MoviesUseCase
import com.example.healthyvirtuosotest.core.abstraction.workers.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase) : ViewModel() {
    private val moviesDispatcher: MutableLiveData<Map<Any, Any>> = MutableLiveData()
    var movies: LiveData<Resource<List<Movie>>> =
        moviesDispatcher.switchMap {
            moviesUseCase.getPopularMovies()
        }

    fun getPopularMovies() {
        moviesDispatcher.value = mapOf("parameter" to "")
    }

}