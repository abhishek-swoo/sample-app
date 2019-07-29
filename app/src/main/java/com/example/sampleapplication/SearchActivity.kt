package com.example.sampleapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.sampleapplication.ViewModel.MainActivityViewModel
import com.example.sampleapplication.ViewModel.SearchActivityViewModel
import kotlinx.android.synthetic.main.search_activity.*
import android.content.Intent
import android.widget.Toast


class SearchActivity : AppCompatActivity() {
    private lateinit var viewModel: SearchActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)
        initUI()
    }

    private fun initUI() {
        viewModel = ViewModelProviders.of(this).get(SearchActivityViewModel::class.java)

        viewModel.dataListResultUIModel.observe(this, Observer {
            it?.let {
                val intent = Intent(this, MainActivity::class.java)
                val list: ArrayList<String>? = it.imageList
                var b = Bundle()
                b.putStringArrayList(Constants.MY_LIST, list)
                intent.putExtra(Constants.DATA, b)
                startActivity(intent)
            }
            if (it == null) {
                Toast.makeText(this, "Network issue", Toast.LENGTH_LONG).show()
            }

        })
        search_btn.setOnClickListener {
            val text = search_et.text
            if (text.isNullOrEmpty()) {
                Toast.makeText(this, "Enter the text!", Toast.LENGTH_LONG).show()
            } else {
                viewModel.getData(text.toString())
            }

        }
    }
}