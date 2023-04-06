package com.example.heroes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.heroes.R
import com.example.heroes.model.ComicResult
import com.example.heroes.model.ComicThumbnail

class ComicBookAdapter(context: Context, items: ArrayList<ComicResult>) : BaseAdapter() {

    private val context: Context
    private val items: ArrayList<ComicResult>

    init {
        this.context = context
        this.items = items
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, toConvertView: View?, parent: ViewGroup?): View? {
        var convertView: View? = toConvertView
        val currentComicResult: ComicResult = items[position]

        if (toConvertView == null) {
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.comic_book_list_view, parent, false)

            val comicImage = convertView.findViewById<ImageView>(R.id.imageView)
            val textViewTitle = convertView.findViewById<TextView>(R.id.textViewTitle)

            Glide.with(context)
                .load(currentComicResult.thumbnail.getThumbnailComicBook())
                .into(comicImage)
            textViewTitle.text = currentComicResult.title
        }

        return convertView
    }
}