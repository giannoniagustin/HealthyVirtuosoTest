package com.example.healthyvirtuosotest.views.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.healthyvirtuosotest.R
import com.example.healthyvirtuosotest.core.abstraction.fragments.BaseFragment
import com.example.healthyvirtuosotest.core.extensions.gone
import com.example.healthyvirtuosotest.core.extensions.visible
import com.example.healthyvirtuosotest.databinding.FragmentMovieDetailBinding
import com.example.healthyvirtuosotest.views.activities.MainActivity

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, MainActivity>() {

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun getBindingClass() = FragmentMovieDetailBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getBindingClass()
        binding.movie = args.movie
        binding.pBar.visible()
        val requestListener = object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                binding.imPoster.visible()
                binding.pBar.gone()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                binding.imPoster.visible()
                binding.imPoster.setImageDrawable(resource)
                binding.pBar.gone()
                return true
            }
        }
        Glide.with(this)
            .load(args.movie.posterPath)
            .error(R.mipmap.ic_image_not_found)
            .listener(requestListener)
            .into(binding.imPoster)

        return binding.root
    }
}