package com.example.heroes.data.remote

import com.example.heroes.model.Comic
import com.example.heroes.utils.AppConstants
import retrofit2.Call
import retrofit2.http.GET

interface ComicApi {
    @GET("/v1/public/comics" + AppConstants.MARVEL_API_QUERY_STRING)
    fun getComics(): Call<Comic>
}