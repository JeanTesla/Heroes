package com.example.heroes.model

data class ComicData(
    val offset: Long,
    val limit: Long,
    val total: Long,
    val count: Long,
    val results: List<ComicResult>
)