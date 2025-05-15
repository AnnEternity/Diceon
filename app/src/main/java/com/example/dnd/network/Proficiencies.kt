package com.example.dnd.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Proficiencies(
    val index: String,
    val name: String?
)
