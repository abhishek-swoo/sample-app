package com.example.sampleapplication.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapplication.ImageNwModel
import com.example.sampleapplication.ImageUIModel
import com.example.sampleapplication.ListDataUIModel
import com.example.sampleapplication.Network.RetrofitProvider
import retrofit2.Call
import retrofit2.Response

class SearchActivityViewModel: ViewModel() {
    private val _dataListResultUIModel = MutableLiveData<ImageUIModel>()
    val dataListResultUIModel: LiveData<ImageUIModel> = _dataListResultUIModel

    val aRetrofitServices by lazy { RetrofitProvider.instance.services }

    fun getData(searchText: String) {
        aRetrofitServices.getDataList(searchText).enqueue(object : retrofit2.Callback<ImageNwModel> {
            override fun onFailure(call: Call<ImageNwModel>, t: Throwable) {
                Log.d("RetrofitCall", "failed: " + t.message + " " + t.stackTrace)
            }

            override fun onResponse(call: Call<ImageNwModel>, response: Response<ImageNwModel>) {
                Log.d("RetrofitCall", "success: " + response.body())
                transformToUIModel(response.body())
            }
        })
    }

    fun transformToUIModel(listData: ImageNwModel?) {
        val listUIData: ArrayList<String>? = ArrayList<String>()
        val thumbnailList: ArrayList<String>? = ArrayList<String>()
        listData?.pageMaps?.forEach {
            it.pageMaps?.image?.first()?.link.let {
                if(it != null)
                    listUIData?.add(it)
            }
            it.pageMaps?.thumbnail?.first()?.link.let {
                if(it!=null) {
                    thumbnailList?.add(it)
                }
            }
        }


        _dataListResultUIModel.postValue(ImageUIModel(
            imageList = listUIData,
            thumbnail = thumbnailList
        ))
    }
}