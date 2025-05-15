package com.example.dnd.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        RaceEntity::class,
        RaceListEntity::class,
        ClassEntity::class,
        ClassProficiencyCrossRef::class,
        ClassListEntity::class,
        ProficiencyEntity::class],

    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao

    companion object {
        fun getDatabase(applicationContext: Context): AppDatabase {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "AppDatabase"
            )
                .fallbackToDestructiveMigration()
                .build()
            return db
        }
    }

}