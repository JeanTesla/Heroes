package com.example.heroes.data.remote

import com.example.heroes.model.Comic
import com.example.heroes.utils.AppConstants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicApi {
    @GET("/v1/public/comics" + AppConstants.MARVEL_API_QUERY_STRING)
    fun getComics(
        @Query("titleStartsWith") titleStartsWith: String?,
        @Query("limit") limit: Int?,
        @Query("dateRange", encoded = true) rangeDate: String?
    ): Call<Comic>
}