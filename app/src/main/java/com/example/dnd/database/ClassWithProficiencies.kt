package com.example.dnd.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ClassWithProficiencies(
    @Embedded val classEntity: ClassEntity,
    @Relation(
        parentColumn = "index",
        entityColumn = "index",
        associateBy = Junction(
            value = ClassProficiencyCrossRef::class,
            parentColumn = "classIndex",
            entityColumn = "proficiencyIndex"
        )
    )
    val proficiencies: List<ProficiencyEntity>
)
