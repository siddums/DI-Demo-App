package com.my.sampleapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.my.sampleapp.R
import com.my.sampleapp.adapter.RecyclerViewAdapter
import com.my.sampleapp.databinding.FragmentGitRepositoryListBinding
import com.my.sampleapp.models.RecyclerData
import com.my.sampleapp.viewmodels.GitRepoListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitRepositoryListFragment : Fragment() {

    private val TAG: String = GitRepositoryListFragment::class.java.getSimpleName()
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var binding: FragmentGitRepositoryListBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_git_repository_list,
            container,
            false
        )

        makeApiCall()

        return binding.root
    }

    private fun initRecyclerView() {
        // initializing recyclerview layout manager
        recyclerViewAdapter = RecyclerViewAdapter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            val decoration =
                DividerItemDecoration(context, MaterialDividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            binding.recyclerView.adapter = recyclerViewAdapter
        }


    }


    // observing data changes after api call
    @SuppressLint("NotifyDataSetChanged")
    fun makeApiCall(): GitRepoListViewModel {

        // initializing viewmodel
        val viewmodel = ViewModelProviders.of(this).get(GitRepoListViewModel::class.java)
        binding.executePendingBindings()

        viewmodel.getRecyclerLiveDataObserver()
            .observe(viewLifecycleOwner, Observer<List<RecyclerData>> {
                if (it != null) {
                    Log.d("TAG", it.toString())
                    initRecyclerView()
                    recyclerViewAdapter.setData(it)
                    recyclerViewAdapter.notifyDataSetChanged()

                } else {
                    Log.e(TAG, "Error while calling api")
                }
            })

        // calling api with parameter query india to fetch details of github repositories belongs to india
        viewmodel.loadData()


        return viewmodel
    }


}