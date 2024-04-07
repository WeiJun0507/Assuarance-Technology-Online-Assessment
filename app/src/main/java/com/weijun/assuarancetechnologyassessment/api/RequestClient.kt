package com.weijun.assuarancetechnologyassessment.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RequestClient {
    companion object {
        val client = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}