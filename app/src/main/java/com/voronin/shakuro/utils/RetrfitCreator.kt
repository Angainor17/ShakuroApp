package com.voronin.shakuro.utils

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit creator based only on URL
 * Gson, Coroutine support
 **/
fun createRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(Gson()))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(baseUrl)
    .build()