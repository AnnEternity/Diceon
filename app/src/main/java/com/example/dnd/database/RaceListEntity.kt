package com.example.dnd.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "race_list")
data class RaceListEntity(
    @PrimaryKey val index: String,
    @ColumnInfo(name = "name") val name: String?
)
