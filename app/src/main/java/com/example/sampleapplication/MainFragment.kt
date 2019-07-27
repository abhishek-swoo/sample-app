package com.example.sampleapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapplication.databinding.FragmentMainBinding
import com.example.sampleapplication.db.AppDatabase
import com.example.sampleapplication.db.MyData
import kotlinx.coroutines.experimental.launch

class MainFragment: Fragment() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val dataDao = AppDatabase.get(context!!.applicationContext).myDataDao()
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        viewModel.dataListResultUIModel.observe(this, Observer { listData ->

            binding.listRv.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
            binding.listRv.adapter = DataListAdapter()
            val adapter = binding.listRv.adapter as DataListAdapter
            listData.let {
                adapter.updateData(listData)
            }

            launch {
                dataDao.insertAll(transformToDBData(listData))
                Log.d("MainActivityTest", "Entry: " + dataDao.findData(2))
            }
        })

        viewModel.getData()
    }

    fun transformToDBData(data: List<ListDataUIModel>): List<MyData> {
        val myData = ArrayList<MyData>()
        data.forEach {
            myData.add(
                MyData(
                    uId = 0,
                    text = it.text,
                    data = it.url
                )
            )
        }
        return myData
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}