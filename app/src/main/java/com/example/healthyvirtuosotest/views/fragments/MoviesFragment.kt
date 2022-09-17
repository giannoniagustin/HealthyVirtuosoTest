package com.example.healthyvirtuosotest.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.healthyvirtuosotest.arch.movies.viemodel.MoviesViewModel
import com.example.healthyvirtuosotest.core.abstraction.fragments.BaseFragment
import com.example.healthyvirtuosotest.core.abstraction.workers.Resource
import com.example.healthyvirtuosotest.databinding.FragmentMoviesBinding
import com.example.healthyvirtuosotest.views.activities.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding, MainActivity>() {

    private val moviesViewModel: MoviesViewModel by viewModels()
    override fun getBindingClass(): FragmentMoviesBinding {
        return FragmentMoviesBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getBindingClass()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesViewModel.movies.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    dismissDialog()
                }
                Resource.Status.LOADING -> {
                    showDialog()
                }
                Resource.Status.ERROR -> {
                    dismissDialog()
                }
            }
        }
        moviesViewModel.getPopularMovies()
    }

    override fun onCreateView(savedInstanceState: Bundle?) {}
}


