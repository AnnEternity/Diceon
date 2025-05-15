package com.example.dnd.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("proficiency_table")
data class ProficiencyEntity(
    @PrimaryKey val index: String,
    @ColumnInfo(name = "name") val name: String?
)
