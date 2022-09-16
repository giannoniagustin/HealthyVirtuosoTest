package com.example.healthyvirtuosotest.core.view.dialog

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.healthyvirtuosotest.R

class ProgressDialog(context: Context) : AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_progress)
        setCancelable(false)

        findViewById<ImageView>(R.id.loading)?.let {
            Glide.with(context).load(R.mipmap.loading_gif).into(
                it
            )
        }
        if (window != null) {
            window!!.setDimAmount(0f)
            window!!.setBackgroundDrawableResource(android.R.color.white)
            window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }
}