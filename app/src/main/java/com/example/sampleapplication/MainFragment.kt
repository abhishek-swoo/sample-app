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
import com.example.sampleapplication.Services.OnItemClickListener
import com.example.sampleapplication.databinding.FragmentMainBinding

class MainFragment: Fragment(), OnItemClickListener {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        viewModel.dataListResultUIModel.observe(this, Observer { listData ->

            binding.listRv.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
            binding.listRv.adapter = DataListAdapter(this)
            val adapter = binding.listRv.adapter as DataListAdapter
            listData.let {
                adapter.updateData(listData)
                Log.d("MainFragment","data: " + listData.toString())
            }
        })

        viewModel.getData()
    }

    override fun onClickListener(data: ListDataUIModel) {

        val fragment =
            activity?.supportFragmentManager?.findFragmentByTag(DetailFragment::class.java.simpleName)
                ?: DetailFragment.newInstance(data)
        val transaction = activity?.supportFragmentManager?.beginTransaction()?.
            addToBackStack(DetailFragment::class.java.simpleName)
            transaction?.replace(R.id.main_container, fragment, DetailFragment::class.java.simpleName)?.commitAllowingStateLoss()

        viewModel.dataSingleResultUIModel.postValue(data)

    }


    companion object {
        fun newInstance() = MainFragment()
    }
}