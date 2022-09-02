package com.my.sampleapp.network

/**
 * @Author: Siddu M S
 * @Email: siddushikkerimath@gmail.com
 * @Date: 02-09-2022
 */

object Validator {

    fun validateGetDataFromAPI(query: String): Boolean {

    return (query.isNotEmpty() && query.length < 20 )

    }
}