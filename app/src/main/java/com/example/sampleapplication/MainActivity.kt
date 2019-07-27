package com.example.sampleapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sampleapplication.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.Bitmap
import com.google.gson.Gson
import java.net.URL
import android.R.id.edit
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.reflect.TypeToken






class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    var savedImages = HashMap<String, String>()
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initUI()
    }

    fun initUI() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.dataListResultUIModel.observe(this, Observer { listData ->

            binding.listRv.layoutManager = GridLayoutManager(this, 2) as RecyclerView.LayoutManager?
            binding.listRv.adapter = DataListAdapter()
            var adapter = binding.listRv.adapter as DataListAdapter
            listData?.let {
                adapter.updateData(it)
                cacheData(it)
            }
            if(listData == null){

                prefs = getSharedPreferences("test", Context.MODE_PRIVATE)
                val storedHashMapString = prefs.getString("hashUrls", "oopsDintWork")
                val type = object : TypeToken<HashMap<String, String>>() {

                }.type

                var gson = Gson()
                val testHashMap: HashMap<String, String> = gson.fromJson(storedHashMapString, type)
                var list = ArrayList<ListDataUIModel>()
                for ((k, v) in testHashMap) {
                    list.add(
                        ListDataUIModel(
                            text = k,
                            url = v))
                }

                adapter.updateData(list)
            }

        })
        viewModel.getData()
    }

    fun cacheData(list: List<ListDataUIModel>) {
        list.forEach {
            savedImages.put(it.text, it.url)
        }

        var gson = Gson()
        val hashMapString = gson.toJson(savedImages)

        prefs = getSharedPreferences("test", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = prefs.edit()
        editor.putString("hashUrls", hashMapString).apply()

    }
}
