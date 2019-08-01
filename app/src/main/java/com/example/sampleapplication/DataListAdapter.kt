package com.example.sampleapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.sampleapplication.Services.OnItemClickListener
import com.example.sampleapplication.databinding.AddDataBinding
import com.example.sampleapplication.databinding.ItemDataListBinding

class DataListAdapter(val listener: OnItemClickListener? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var dataListUI: List<ListDataUIModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            VIEW_TYPE_DATA -> {
                val binding = DataBindingUtil.inflate<ItemDataListBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_data_list,
                    parent,
                    false
                )

                return DataListViewHolder(binding)
            }

            else -> {
                val binding = DataBindingUtil.inflate<AddDataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.add_data,
                    parent,
                    false
                )
                return DataADDHolder(binding)
            }
        }

    }

    companion object {
        private const val VIEW_TYPE_DATA = 1
        private const val VIEW_TYPE_ADD = 2
    }

    override fun getItemViewType(position: Int): Int {

        if(dataListUI?.get(position)?.rent == -1L) return VIEW_TYPE_ADD
        else return VIEW_TYPE_DATA
    }

    fun updateData(listDatumUIS: List<ListDataUIModel>) {
        dataListUI = listDatumUIS
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        val size = dataListUI!!.size
        if (dataListUI == null) {
            return 0
        } else {
            return size
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataPos = dataListUI?.get(position)

        when (holder) {
            is DataListViewHolder -> {
                holder.bindTo(dataPos, listener)
            }
            is DataADDHolder -> {
                holder.bindTo()
            }
        }
    }

}

class DataListViewHolder(
    val binding: ItemDataListBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(dataUI: ListDataUIModel?, listener: OnItemClickListener?) {

        dataUI?.let {
            binding.rent.text = it.rent.toString()
            binding.title.text = it.title
            val imageUrl = "http:" + it.thumbnailImage
            Log.d("DataListAdapter", "url: " + imageUrl)
            Glide.with(binding.dataIv.context)
                .load(imageUrl)
                .centerCrop().diskCacheStrategy(
                DiskCacheStrategy.ALL
            ).into(binding.dataIv)

            binding.dataIv.setOnClickListener {
                listener?.onClickListener(dataUI)
            }
        }

//        https://images.unsplash.com/photo-1523676060187-f55189a71f5e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80
    }
}

class DataADDHolder(
    val binding: AddDataBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo() {

    }
}