package com.example.healthyvirtuosotest.core.holders

import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.healthyvirtuosotest.R
import com.example.healthyvirtuosotest.arch.movies.domain.model.Movie
import com.example.healthyvirtuosotest.core.abstraction.adapters.Holder
import com.example.healthyvirtuosotest.core.extensions.gone
import com.example.healthyvirtuosotest.databinding.MovieCardBinding


open class MovieHolder(private val item: MovieCardBinding) : Holder(item) {
    open fun bind(movie: Movie) {
        item.movie = movie
        val requestListener = object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                item.pBar.gone()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                item.pBar.gone()
                item.imgMovie.setImageDrawable(resource)
                return true
            }
        }
        Glide.with(itemView.context)
            .load(movie.posterPath)
            .error(R.mipmap.ic_image_not_found)
            .listener(requestListener)
            .into(item.imgMovie)
    }
}