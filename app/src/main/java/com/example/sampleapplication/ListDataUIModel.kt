package com.example.sampleapplication

import com.google.gson.annotations.SerializedName


data class ListDataUIModel (
    val id: String,
    val title: String,
    val thumbnailImage: String,
    val nbLocality: String,
    val parkingDesc: String,
    val rent: Long
)

data class DataNwModel (
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("thumbnailImage") val thumbnailImage: String?,
    @SerializedName("nbLocality") val nbLocality: String?,
    @SerializedName("parkingDesc") val parkingDesc: String?,
    @SerializedName("rent") val rent: Long?
)

data class ListDataNwModel(
    @SerializedName("data") val list: List<DataNwModel>?
)