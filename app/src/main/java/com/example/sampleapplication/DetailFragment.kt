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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.sampleapplication.databinding.DetailFragmentBinding

class DetailFragment: Fragment() {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)


        setUIView()

        viewModel.dataSingleResultUIModel.observe(this, Observer {

            it?.let {
                binding.price.text = it.rent.toString()
                binding.title.text = it.title
                Glide.with(this).load(it.thumbnailImage).centerCrop().diskCacheStrategy(
                    DiskCacheStrategy.ALL).into(binding.image)
            }
        })
    }

    private fun setUIView() {
        binding.price.text = arguments?.getString("title")
        binding.title.text = arguments?.getString("rent")
        Glide.with(this).load(arguments?.get("image")).centerCrop().diskCacheStrategy(
            DiskCacheStrategy.ALL
        ).into(binding.image)

    }

    companion object {

        fun newInstance(dataUIModel: ListDataUIModel): androidx.fragment.app.Fragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putString("thumbnail", dataUIModel.thumbnailImage)
            args.putString("rent", dataUIModel.rent.toString())
            args.putString("title", dataUIModel.title)
            fragment.arguments = args
            return fragment
        }
    }
}