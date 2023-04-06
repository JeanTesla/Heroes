package com.example.heroes.model

data class ComicThumbnail(
    val path: String,
    val extension: String
) {
    fun getThumbnailComicBook(): String {
        return "$path.$extension"
    }
}