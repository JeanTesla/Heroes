package com.example.heroes.config

import com.example.heroes.utils.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    companion object {
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(AppConstants.MARVEL_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}