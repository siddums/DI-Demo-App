package com.my.sampleapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.my.sampleapp.models.RecyclerData
import com.my.sampleapp.repository.RetroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: Siddu M S
 * @Email: siddushikkerimath@gmail.com
 * @Date: 29-08-2022
 */

@HiltViewModel
class GitRepoListViewModel @Inject constructor(private val repository: RetroRepository): ViewModel() {

    private val TAG: String = GitRepoListViewModel::class.java.getSimpleName()

    var recyclerListData: MutableLiveData<List<RecyclerData>>


    // Initializing data members, same as primary constructor in java
    init {
        recyclerListData = MutableLiveData()
    }

    // returns the recyclerview data
    fun getRecyclerLiveDataObserver(): MutableLiveData<List<RecyclerData>> {
        return recyclerListData
    }

    // making api call with query param
    fun loadData(){
        repository.makeApiCall("India",recyclerListData)
    }

}