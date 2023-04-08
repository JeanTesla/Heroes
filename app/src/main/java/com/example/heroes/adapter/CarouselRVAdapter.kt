package com.example.heroes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.heroes.R
import com.example.heroes.model.ComicThumbnail

class CarouselRVAdapter(private val carouselDataList: ArrayList<ComicThumbnail>) :
        RecyclerView.Adapter<CarouselItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselItemViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        return CarouselItemViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: CarouselItemViewHolder, position: Int) {
        val item: ComicThumbnail = carouselDataList[position]
        Glide.with(holder.contextView).load(item.getThumbnailUrlComicBook())
            .into(holder.imageView);
    }

    override fun getItemCount() = carouselDataList.size
}