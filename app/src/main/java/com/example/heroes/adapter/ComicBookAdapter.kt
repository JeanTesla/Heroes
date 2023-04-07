package com.example.heroes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.heroes.R
import com.example.heroes.model.ComicResult

class ComicBookAdapter(private val dataSet: ArrayList<ComicResult>,
                       private val onClickListItemCallBack: (ComicResult) -> Unit) :
    RecyclerView.Adapter<ComicBookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicBookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comic_book_list_view, parent, false)

        return ComicBookViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ComicBookViewHolder, position: Int) {
        val item: ComicResult = dataSet[position]

        holder.getView().setOnClickListener{
            onClickListItemCallBack(item)
        }

        Glide.with(holder.contextView).load(item.thumbnail.getThumbnailUrlComicBook())
            .into(holder.imageView);

        holder.textViewTitle.text = item.title
    }

}