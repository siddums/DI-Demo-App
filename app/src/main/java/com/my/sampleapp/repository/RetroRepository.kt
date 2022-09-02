package com.my.sampleapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.my.sampleapp.models.RecyclerData
import com.my.sampleapp.models.RecyclerList
import com.my.sampleapp.network.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * @Author: Siddu M S
 * @Email: siddushikkerimath@gmail.com
 * @Date: 01-09-2022
 */
class RetroRepository @Inject constructor(private val retroService: RetroService) {


    fun makeApiCall(query: String, liveDataList: MutableLiveData<List<RecyclerData>>){
        val call: Call<RecyclerList> = retroService.getDataFromApi(query)
    call?.enqueue(object : Callback<RecyclerList>{
        override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
            Log.d("TAGG","Inside repo data "+response.body().toString())
            liveDataList.postValue(response.body()?.items!!)
        }

        override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
            liveDataList.postValue(null)
        }

    })
    }
}