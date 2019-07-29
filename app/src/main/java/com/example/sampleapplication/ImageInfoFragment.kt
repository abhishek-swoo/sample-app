package com.example.sampleapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.sampleapplication.databinding.ImageInfoFragmentBinding

class ImageInfoFragment: Fragment() {

    private lateinit var binding: ImageInfoFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.image_info_fragment,
            container,
            false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initUI()
    }

    private fun initUI() {
        val urlData = getImageData()
        urlData?.let {
            binding.dataTv.text = it
        }
    }

    private fun getImageData(): String? {
        return arguments?.getString(Constants.DATA)
    }

    companion object {

        fun newInstance(url: String?): ImageInfoFragment {
            val bundle = Bundle().apply {
                putString(Constants.DATA, url)
            }
            val frag = ImageInfoFragment()
            frag.arguments = bundle
            return frag
        }
    }


}