package com.example.sampleapplication.Network

import com.example.sampleapplication.DataNwModel
import com.example.sampleapplication.ListDataNwModel
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {

    @GET("/getProperties")
    fun getDataList(): Call<ListDataNwModel>

}