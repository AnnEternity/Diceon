package com.example.dnd.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("class_list_entity")
data class ClassListEntity(
    @PrimaryKey val index: String,
    @ColumnInfo(name = "name") val name: String?
)
