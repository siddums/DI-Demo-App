package com.my.sampleapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Author: Siddu M S
 * @Email: siddushikkerimath@gmail.com
 * @Date: 29-08-2022
 */
class RetroInstance {

    companion object{

        val BASE_URL = "https://api.github.com/search/"

        fun getRetroInstance(): Retrofit{

          return  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }        }
}