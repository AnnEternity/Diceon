package com.example.dnd.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseClasses(
    val count: Int?,
    val results: List<ResponseClassesResult>?
)