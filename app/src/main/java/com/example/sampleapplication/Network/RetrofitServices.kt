package com.example.sampleapplication.Network

import com.example.sampleapplication.ImageNwModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServices {

    @GET("/customsearch/v1?cx=011476162607576381860:ra4vmliv9ti&key=AIzaSyCIjxAB5-8nEEhIx0fN9UHg1VgJGny2Im4")
    fun getDataList(@Query("q") searchText: String): Call<ImageNwModel>

}