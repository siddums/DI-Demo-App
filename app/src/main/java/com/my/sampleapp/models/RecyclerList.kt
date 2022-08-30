package com.my.sampleapp.models

/**
 * @Author: Siddu M S
 * @Email: siddushikkerimath@gmail.com
 * @Date: 29-08-2022
 */

// model class for holding response data
data class RecyclerList(val items: ArrayList<RecyclerData>)

// model class for holding item details like name, description, created date and owner details
data class RecyclerData(
    val name: String,
    val description: String,
    val created_at: String,
    var owner: Owner
)

// model class for holding owner data like image url
data class Owner(val avatar_url: String)