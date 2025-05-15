package com.example.dnd.database

import androidx.room.Entity

@Entity("class_proficiency_cross_ref", primaryKeys = ["classIndex", "proficiencyIndex"])
data class ClassProficiencyCrossRef(
    val classIndex: String,
    val proficiencyIndex: String
)