package com.example.healthyvirtuosotest.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthyvirtuosotest.core.abstraction.fragments.BaseFragment
import com.example.healthyvirtuosotest.databinding.FragmentMoviesBinding
import com.example.healthyvirtuosotest.views.activities.MainActivity

class MoviesFragment : BaseFragment<FragmentMoviesBinding, MainActivity>() {

    override fun getBindingClass(): FragmentMoviesBinding {
        return FragmentMoviesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getBindingClass()
        return binding.root
    }

    override fun onCreateView(savedInstanceState: Bundle?) {}
}

