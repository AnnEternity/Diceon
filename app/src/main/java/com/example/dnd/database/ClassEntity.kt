package com.example.dnd.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("class_table")
data class ClassEntity(
    @PrimaryKey val index: String,
    val name: String?,
    @ColumnInfo(name = "hit_die")val hitDie: Int?
)
