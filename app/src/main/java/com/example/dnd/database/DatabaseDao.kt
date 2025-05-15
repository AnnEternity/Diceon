package com.example.dnd.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface DatabaseDao {

    //race
    @Query("SELECT * FROM race_list")
    fun getAllRaces(): LiveData<List<RaceListEntity>>

    @Query("SELECT * FROM race WHERE `index` = :index")
    fun getRace(index: String): LiveData<RaceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg races: RaceListEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(race: RaceEntity)

    //class
    @Query("SELECT * FROM class_list_entity")
    fun getAllClasses(): LiveData<List<ClassListEntity>>

    @Transaction
    @Query("SELECT * FROM class_table WHERE `index` = :index")
    fun getClass(index: String): LiveData<ClassWithProficiencies>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllClasses(vararg classes: ClassEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveClassList(vararg classList: ClassListEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllProficiencies(vararg proficiencies: ProficiencyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllClassProficiencyCrossRef(vararg classProficiencyCrossRef: ClassProficiencyCrossRef)

}