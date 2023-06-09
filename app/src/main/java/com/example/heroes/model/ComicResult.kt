package com.example.heroes.model

data class ComicResult (
    val id: Long,
    val digitalId: Long,
    val title: String,
    val issueNumber: Long,
    val variantDescription: String,
    val description: String?,
    val modified: String,
    val isbn: String,
    val upc: String,
    val diamondCode: String,
    val ean: String,
    val issn: String,
    val format: String,
    val pageCount: Long,
    val textObjects: List<ComicTextObject>,
    val resourceURI: String,
    val variants: List<Any?>,
    val collections: List<Any?>,
    val collectedIssues: List<Any?>,
    val dates: List<ComicDate>,
    val prices: List<ComicPrice>,
    val thumbnail: ComicThumbnail,
    val images: List<ComicThumbnail>,
    val creators: ComicCreators
)