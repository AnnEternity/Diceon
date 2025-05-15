package com.example.dnd.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Class(
    val index: String,
    val name: String?,
    @Json(name = "hit_die") val hitDie: Int?,
    val proficiencies: List<Proficiencies>
)
