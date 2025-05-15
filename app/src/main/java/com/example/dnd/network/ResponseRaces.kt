package com.example.dnd.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseRaces(
    val count: Int?,
    val results: List<ResponseRacesResult>?
)
