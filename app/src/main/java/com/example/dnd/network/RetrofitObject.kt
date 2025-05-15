package com.example.dnd.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object RetrofitObject {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.dnd5eapi.co")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val service = retrofit.create<ApiService>()
}