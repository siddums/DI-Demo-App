package com.my.sampleapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.my.sampleapp.R
import com.my.sampleapp.databinding.RecyclerviewListItemsBinding
import com.my.sampleapp.models.RecyclerData

/**
 * @Author: Siddu M S
 * @Email: siddushikkerimath@gmail.com
 * @Date: 29-08-2022
 */
class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {


    var items = ArrayList<RecyclerData>()

    fun setData(data: ArrayList<RecyclerData>) {
        items = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewListItemsBinding.inflate(layoutInflater)

        return MyViewHolder(binding)


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class MyViewHolder(val binding: RecyclerviewListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RecyclerData) {
            binding.data = data
            binding.executePendingBindings()
        }
    }

    companion object {
        // required to make static annotation to avoid crash
        @JvmStatic

        // loading remote image url into imageview using glide library
        @BindingAdapter("loadImage")
        fun loadImage(thumbImage: ImageView, url: String) {
            Glide.with(thumbImage)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .fallback(R.drawable.ic_launcher_background)
                .into(thumbImage)
        }

    }

}