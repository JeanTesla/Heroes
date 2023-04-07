package com.example.heroes.model

data class ComicThumbnail(
    val path: String,
    val extension: String
) {
    fun getThumbnailUrlComicBook(): String {
        return "$path.$extension"
    }
}