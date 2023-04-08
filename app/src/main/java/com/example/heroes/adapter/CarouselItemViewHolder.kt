package com.example.heroes.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.heroes.R

class CarouselItemViewHolder(view: View): RecyclerView.ViewHolder(view){
    val contextView: Context = view.context
    val imageView: ImageView = view.findViewById(R.id.image_view_carousel_item)
}

