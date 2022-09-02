package com.my.sampleapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.my.sampleapp.R
import com.my.sampleapp.databinding.FragmentDetailsBinding
import com.my.sampleapp.models.RecyclerData


class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    lateinit var item: RecyclerData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

            val data = arguments?.getString("data")
            val gson = Gson()
            item = gson.fromJson(data.toString(), RecyclerData::class.java)

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details,
            container,
            false
        )
        Glide.with(binding.imageView2)
            .load(item.owner.avatar_url)
            .circleCrop()
            .placeholder(com.my.sampleapp.R.drawable.ic_launcher_background)
            .error(com.my.sampleapp.R.drawable.ic_launcher_background)
            .fallback(com.my.sampleapp.R.drawable.ic_launcher_background)
            .into(binding.imageView2)
        binding.data = item


        return binding.root
    }


}