package com.my.sampleapp.network

import com.my.sampleapp.models.RecyclerData
import com.my.sampleapp.models.RecyclerList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Author: Siddu M S
 * @Email: siddushikkerimath@gmail.com
 * @Date: 29-08-2022
 */
interface RetroService {

    @GET("repositories")
    fun getDataFromApi(@Query("q") query:String):Call<RecyclerList>
}