package com.example.heroes.model

data class ComicCreators (
    val available: Long,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Long
)