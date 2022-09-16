package com.example.healthyvirtuosotest.core.abstraction.adapters

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * View Holder base class
 *
 * @author Agustin Giannoni
 * @version 1.0
 */
abstract class Holder(itemView: ViewBinding) : RecyclerView.ViewHolder(itemView.root) {}
