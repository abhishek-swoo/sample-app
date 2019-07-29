package com.example.sampleapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.sampleapplication.ClickListeners.ImageClickListener
import com.example.sampleapplication.databinding.ItemDataListBinding

class DataListAdapter(val listener: ImageClickListener?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var dataListUI: ArrayList<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemDataListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_data_list,
            parent,
            false
        )

        return DataListViewHolder(binding)
    }

    fun updateData(listData: ArrayList<String>?) {
        dataListUI = listData
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if (dataListUI == null) {
            return 0
        } else {
            return dataListUI!!.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataPos = dataListUI?.get(position)

        when (holder) {
            is DataListViewHolder -> {
                holder.bindTo(dataPos, clickListener = listener)
            }
        }
    }

}

class DataListViewHolder(
    val binding: ItemDataListBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(dataUIPos: String?, clickListener: ImageClickListener?) {
        dataUIPos?.let {
            Glide.with(binding.dataIv.context).load(dataUIPos).centerCrop().into(binding.dataIv)
        }
        Log.d("adapterImage", "url: " + dataUIPos)
        binding.dataIv.setOnClickListener {
            clickListener?.onImageClickListener(dataUIPos)
        }
    }
}