package com.example.heroes.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.heroes.R

class ComicBookViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    val contextView: Context = view.context
    val imageView: ImageView = view.findViewById(R.id.imageView)
    val textViewTitle: TextView = view.findViewById(R.id.textViewTitle)

    fun getView(): View{
        return view
    }
}