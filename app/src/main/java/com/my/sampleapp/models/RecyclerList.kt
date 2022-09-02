package com.my.sampleapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.io.Serializable

/**
 * @Author: Siddu M S
 * @Email: siddushikkerimath@gmail.com
 * @Date: 29-08-2022
 */

// model class for holding response data
@Parcelize
data class RecyclerList(val items: @RawValue ArrayList<RecyclerData>) : Serializable, Parcelable

// model class for holding item details like name, description, created date and owner details
data class RecyclerData(
    val name: @RawValue String,
    val description:@RawValue  String,
    val created_at: @RawValue String,
    val visibility: @RawValue String,
    val forks: @RawValue String,
    val open_issues:@RawValue  String,
    val watchers: @RawValue String,
    val score: @RawValue String,
    val default_branch: @RawValue String,
    var owner: @RawValue Owner
)

// model class for holding owner data like image url
data class Owner(val avatar_url: @RawValue String, val login: @RawValue String)