package com.example.dnd.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseClassesResult(
    val index: String?,
    val name: String?
)
