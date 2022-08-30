package com.my.sampleapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.my.sampleapp.adapter.RecyclerViewAdapter
import com.my.sampleapp.models.RecyclerData
import com.my.sampleapp.models.RecyclerList
import com.my.sampleapp.network.RetroInstance
import com.my.sampleapp.network.RetroService
import com.my.sampleapp.ui.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @Author: Siddu M S
 * @Email: siddushikkerimath@gmail.com
 * @Date: 29-08-2022
 */
class MainActivityViewModel : ViewModel() {

    private val TAG: String = MainActivityViewModel::class.java.getSimpleName()

    var recyclerListData: MutableLiveData<RecyclerList>
    var recyclerViewAdapter: RecyclerViewAdapter

    // Initializing data members, same as primary constructor in java
    init {
        recyclerListData = MutableLiveData()
        recyclerViewAdapter = RecyclerViewAdapter()

    }

    // getting adapter recyclerview adapter
    fun getAdapter(): RecyclerViewAdapter{
        return  recyclerViewAdapter
    }

    // setting data to adapter after api call response
    fun setAdapterData(data: ArrayList<RecyclerData>){
        recyclerViewAdapter.setData(data)
        recyclerViewAdapter.notifyDataSetChanged()

    }

    // returns the recyclerview data
    fun getRecyclerLiveDataObserver(): MutableLiveData<RecyclerList> {
        return recyclerListData
    }

    // getting remote data from retrofit
    fun makeApiCall(input: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromApi(input)
        Log.i(TAG,"URL "+call.request().url())

        // requesting callback for response after api request
        call.enqueue(object : Callback<RecyclerList> {

            // on failure setting recyclerview data to null
            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                Log.e(TAG, "Error while calling api")
                recyclerListData.postValue(null)
            }

            // on receiving response checking for sucess and fetch the response and  updating livedata
            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if (response.isSuccessful) {
                    Log.d(TAG, " Data fetched from internet ")
                    recyclerListData.postValue(response.body())
                } else {
                    Log.d(TAG, "Response is NULL")
                    recyclerListData.postValue(null)
                }

            }
        })
    }
}