package com.example.sampleapplication

import com.example.sampleapplication.ViewModel.MainActivityViewModel
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
import com.example.sampleapplication.ClickListeners.ImageClickListener
import com.example.sampleapplication.databinding.FragmentMainBinding

class MainFragment: Fragment(), ImageClickListener {


    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initUI()
    }

    private fun initUI() {

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        val imageList = arguments!!.getStringArrayList(Constants.DATA)


        binding.listRv.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        binding.listRv.adapter = DataListAdapter(this)
        val adapter = binding.listRv.adapter as DataListAdapter
        adapter.updateData(imageList)
    }

    override fun onImageClickListener(item: String?) {
        val fragment = ImageInfoFragment.newInstance(item)

        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_container, fragment, ImageInfoFragment::class.java.simpleName)?.commit()
    }


    companion object {
        fun newInstance(imageList: ArrayList<String>?): MainFragment {
            val bundle = Bundle().apply {
                putStringArrayList(Constants.DATA, imageList)
            }
            val fragment = MainFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}