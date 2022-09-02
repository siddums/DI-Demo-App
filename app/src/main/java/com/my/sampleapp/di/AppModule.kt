package com.my.sampleapp.di

import com.my.sampleapp.network.RetroService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @Author: Siddu M S
 * @Email: siddushikkerimath@gmail.com
 * @Date: 01-09-2022
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val BASE_URL = "https://api.github.com/search/"

    @Singleton
    @Provides
    fun getRetroInstance(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Singleton
    @Provides
    fun getRetroService(retrofit: Retrofit): RetroService {

        return retrofit.create(RetroService::class.java)
    }
}