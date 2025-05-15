package com.example.dnd.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Race(
    val index: String?,
    val name: String?,
    val alignment: String?
)
