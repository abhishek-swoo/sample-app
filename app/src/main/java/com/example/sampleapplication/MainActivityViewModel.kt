package com.example.sampleapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapplication.Network.RetrofitProvider
import retrofit2.Call
import retrofit2.Response


class MainActivityViewModel : ViewModel() {

    private val _dataListResultUIModel = MutableLiveData<List<ListDataUIModel>>()
    val dataListResultUIModel: LiveData<List<ListDataUIModel>> = _dataListResultUIModel

    val dataSingleResultUIModel = MutableLiveData<ListDataUIModel>()

    val aRetrofitServices by lazy { RetrofitProvider.instance.services }

    fun getData() {
        aRetrofitServices.getDataList().enqueue(object : retrofit2.Callback<ListDataNwModel> {
            override fun onFailure(call: Call<ListDataNwModel>, t: Throwable) {
                Log.d("RetrofitCall", "failed: " + t.message + " " + t.stackTrace)
            }

            override fun onResponse(call: Call<ListDataNwModel>, response: Response<ListDataNwModel>) {
                Log.d("RetrofitCall", "success: " + response.body())
                transformToUIModel(response.body())
            }
        })
    }

    fun transformToUIModel(listData: ListDataNwModel?) {
        var count = 0
        var listUIData = ArrayList<ListDataUIModel>()
        listData?.list?.forEach {

            addAdd(count, listUIData)

            if (it.id != null && it.nbLocality != null && it.title != null && it.parkingDesc != null && it.thumbnailImage != null && it.rent != null) {
                listUIData.add(
                    ListDataUIModel(
                        id = it.id,
                        nbLocality = it.nbLocality,
                        thumbnailImage = it.thumbnailImage,
                        title = it.title,
                        parkingDesc = it.parkingDesc,
                        rent = it.rent
                    )
                )
                count++
            }

        }
        _dataListResultUIModel.postValue(listUIData)
    }

    private fun addAdd(
        count: Int,
        listUIData: ArrayList<ListDataUIModel>
    ) {
        if (count % 5 == 0 && count != 0) {
            listUIData.add(
                ListDataUIModel(
                    id = "",
                    nbLocality = "",
                    thumbnailImage = "",
                    title = "",
                    parkingDesc = "",
                    rent = -1
                )
            )
        }
    }

}