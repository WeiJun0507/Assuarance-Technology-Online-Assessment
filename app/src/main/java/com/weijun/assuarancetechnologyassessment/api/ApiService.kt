package com.weijun.assuarancetechnologyassessment.api

import com.weijun.assuarancetechnologyassessment.model.data.MovieData
import com.weijun.assuarancetechnologyassessment.model.data.RequestWrapperData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object {
        val apiService: ApiService = RequestClient.client.create(ApiService::class.java)
    }

    @GET("?apikey=6fc87060&s=Marvel&type=movie")
    fun getMovieList(): Call<RequestWrapperData>

    @GET("?apikey=6fc87060")
    fun getMovieDetail(@Query("i") imdbId: String): Call<MovieData>
}