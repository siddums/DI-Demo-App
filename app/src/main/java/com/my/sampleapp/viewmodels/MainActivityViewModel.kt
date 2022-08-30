package com.my.sampleapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.my.sampleapp.adapter.RecyclerViewAdapter
import com.my.sampleapp.models.RecyclerData
import com.my.sampleapp.models.RecyclerList
import com.my.sampleapp.network.RetroInstance
import com.my.sampleapp.network.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @Author: Siddu M S
 * @Email: siddushikkerimath@gmail.com
 * @Date: 29-08-2022
 */
class MainActivityViewModel : ViewModel() {

    lateinit var recyclerListData: MutableLiveData<RecyclerList>
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    // same as primary constructor in java
    init {
        recyclerListData = MutableLiveData()
        recyclerViewAdapter = RecyclerViewAdapter()

    }

    fun getAdapter(): RecyclerViewAdapter{
        return  recyclerViewAdapter
    }

    fun setAdapterData(data: ArrayList<RecyclerData>){
        recyclerViewAdapter.setData(data)
        recyclerViewAdapter.notifyDataSetChanged()

    }
    fun getRecyclerLiveDataObserver(): MutableLiveData<RecyclerList> {
        return recyclerListData
    }

    fun makeApiCall(input: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromApi(input)
        Log.e("TAG","URL "+call.request().url())
        call.enqueue(object : Callback<RecyclerList> {
            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                Log.e("TAG", "Error while calling api")
                recyclerListData.postValue(null)
            }

            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if (response.isSuccessful) {
                    Log.d("TAG", " Data fetched from internet ")
                   // Log.d("TAG", " Data fetched from internet "+response.body().toString())
                    recyclerListData.postValue(response.body())
                } else {
                    Log.i("TAG", "Response is NULL")
                    recyclerListData.postValue(null)
                }

            }
        })
    }
}