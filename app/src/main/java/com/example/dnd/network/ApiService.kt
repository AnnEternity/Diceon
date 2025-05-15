package com.example.dnd.network

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api/2014/races")
    suspend fun getListRaces(): ResponseRaces

    @GET("/api/2014/races/{index}")
    suspend fun getRaceByIndex(@Path("index") index: String): Race

    @GET("/api/2014/classes")
    suspend fun getListClasses(): ResponseClasses

    @GET("/api/2014/classes/{index}")
    suspend fun getClassByIndex(@Path("index") index: String): Class

}