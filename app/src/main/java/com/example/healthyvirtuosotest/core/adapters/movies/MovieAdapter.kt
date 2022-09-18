package com.example.healthyvirtuosotest.core.adapters.movies

import android.content.Context
import android.view.View
import com.example.healthyvirtuosotest.R
import com.example.healthyvirtuosotest.arch.movies.domain.model.Movie
import com.example.healthyvirtuosotest.core.abstraction.adapters.CollectionAdapter
import com.example.healthyvirtuosotest.core.holders.MovieHolder
import com.example.healthyvirtuosotest.databinding.MovieCardBinding

class MovieAdapter(context: Context) :
    CollectionAdapter<Movie, MovieHolder>(context) {
    override fun createHolder(cardView: View, viewType: Int) =
        MovieHolder(MovieCardBinding.bind(cardView))

    override fun resource(viewType: Int) = R.layout.movie_card

    override fun bindHolder(item: Movie, holder: MovieHolder, position: Int) =
        holder.bind(item)
}