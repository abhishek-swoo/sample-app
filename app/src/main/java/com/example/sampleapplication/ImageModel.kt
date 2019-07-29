package com.example.sampleapplication

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize



data class ImageNwModel(@SerializedName("items") val pageMaps: List<Items>?)

data class Items(@SerializedName("pagemap") val pageMaps: CseImage?)

data class CseImage(@SerializedName("cse_image") val image: List<Src>?,
                    @SerializedName("cse_thumbnail") val thumbnail: List<Src>?)

data class Src(@SerializedName("src") val link: String?)

data class ImageUIModel(val thumbnail: ArrayList<String>?,
                        val imageList: ArrayList<String>?)
