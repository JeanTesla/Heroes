package com.example.heroes.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.heroes.config.RetrofitConfig
import com.example.heroes.data.remote.ComicApi
import com.example.heroes.model.Comic
import com.example.heroes.model.ComicResult
import com.example.heroes.model.QueryStringCreatorComicSearch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    companion object {

        var comicRequestError = MutableLiveData<Boolean>()
        var comicBookList = MutableLiveData<List<ComicResult>>()

        private val retrofitClient = RetrofitConfig
            .getRetrofitInstance()
            .create(ComicApi::class.java)

        fun getAllComics(queryString: QueryStringCreatorComicSearch) {

            retrofitClient.getComics(
                queryString.titleStartsWith,
                queryString.limit,
                queryString.getConcatenatedDateRange()).enqueue(object : Callback<Comic> {

                override fun onResponse(call: Call<Comic>, response: Response<Comic>) {
                    comicBookList.value = response.body()?.data?.results
                }

                override fun onFailure(call: Call<Comic>, t: Throwable) {
                    comicRequestError.value = true
                }
            })
        }
    }
}