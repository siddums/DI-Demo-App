package com.my.sampleapp.models

/**
 * @Author: Siddu M S
 * @Email: siddushikkerimath@gmail.com
 * @Date: 29-08-2022
 */
data class RecyclerList(val items: ArrayList<RecyclerData>)
data class RecyclerData(val name: String,val description:String,val created_at:String, var owner:Owner)
data class Owner(val avatar_url: String)