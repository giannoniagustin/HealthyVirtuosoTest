package com.example.healthyvirtuosotest.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthyvirtuosotest.arch.movies.domain.model.Movie
import com.example.healthyvirtuosotest.arch.movies.viemodel.MoviesViewModel
import com.example.healthyvirtuosotest.core.abstraction.adapters.CollectionAdapter
import com.example.healthyvirtuosotest.core.abstraction.fragments.BaseFragment
import com.example.healthyvirtuosotest.core.abstraction.workers.Resource
import com.example.healthyvirtuosotest.core.adapters.movies.MovieAdapter
import com.example.healthyvirtuosotest.core.extensions.initGrid
import com.example.healthyvirtuosotest.core.holders.MovieHolder
import com.example.healthyvirtuosotest.databinding.FragmentMoviesBinding
import com.example.healthyvirtuosotest.views.activities.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding, MainActivity>() {

    private val moviesViewModel: MoviesViewModel by viewModels()
    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter(requireContext())
    }

    override fun getBindingClass(): FragmentMoviesBinding {
        return FragmentMoviesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesViewModel.getMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getBindingClass()
        binding.rvMovies.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvMovies.initGrid(
            dataSet = movieAdapter,
            span = 1,
            orientation = RecyclerView.VERTICAL,
            onItemClickListener = object :
                CollectionAdapter.OnItemClickListener<Movie, MovieHolder> {
                override fun onItemClick(item: Movie, holder: MovieHolder, position: Int) {
                    Toast.makeText(requireContext(), "Item click ${item.title}", Toast.LENGTH_SHORT)
                        .show()
                    navigation.navigate(
                        MoviesFragmentDirections.actionRootFragmentToMovieDetailFragment(
                            item
                        )
                    )
                }
            })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesViewModel.movies.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    movieAdapter.dataSet = it.data!!.movieResults.toMutableList()
                    binding.rvMovies.adapter = movieAdapter
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
    }

    override fun onResume() {
        super.onResume()
    }
}


