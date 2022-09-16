package com.example.healthyvirtuosotest.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthyvirtuosotest.core.abstraction.fragments.BaseFragment
import com.example.healthyvirtuosotest.databinding.FragmentMovieDetailBinding
import com.example.healthyvirtuosotest.views.activities.MainActivity

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, MainActivity>() {

    override fun getBindingClass() = FragmentMovieDetailBinding.inflate(layoutInflater)

    override fun onCreateView(savedInstanceState: Bundle?) {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getBindingClass()
        return binding.root
    }
}