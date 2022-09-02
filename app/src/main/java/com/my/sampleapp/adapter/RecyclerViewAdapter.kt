package com.my.sampleapp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.databinding.BindingAdapter
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.my.sampleapp.databinding.RecyclerviewListItemsBinding
import com.my.sampleapp.models.RecyclerData


/**
 * @Author: Siddu M S
 * @Email: siddushikkerimath@gmail.com
 * @Date: 29-08-2022
 */
class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {


    private var items : List<RecyclerData>? = null

    fun setData(data: List<RecyclerData>?) {
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
        holder.bind(items?.get(position)!!)
        holder.itemView.setOnClickListener {
            val gson = Gson()
            val bundle = bundleOf("data" to gson.toJson(items?.get(position)))
            Navigation.findNavController(holder.binding.root)
                .navigate(
                    com.my.sampleapp.R.id.action_gitRepositoryListFragment_to_detailsFragment,
                    bundle
                )
        }
    }

    override fun getItemCount() = items?.size ?: 0

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
                .placeholder(com.my.sampleapp.R.drawable.ic_launcher_background)
                .error(com.my.sampleapp.R.drawable.ic_launcher_background)
                .fallback(com.my.sampleapp.R.drawable.ic_launcher_background)
                .into(thumbImage)
        }

    }

}

