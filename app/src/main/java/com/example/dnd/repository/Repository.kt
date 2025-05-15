package com.example.dnd.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.dnd.database.AppDatabase
import com.example.dnd.database.ClassEntity
import com.example.dnd.database.ClassListEntity
import com.example.dnd.database.ClassProficiencyCrossRef
import com.example.dnd.database.ClassWithProficiencies
import com.example.dnd.database.ProficiencyEntity
import com.example.dnd.database.RaceEntity
import com.example.dnd.database.RaceListEntity
import com.example.dnd.network.ApiService

private const val TAG = "Repository"

class Repository(
    val database: AppDatabase,
    val apiService: ApiService
) {
    //command option f (extract as field)
    private val databaseDao = database.databaseDao()

    //race
    val racesList: LiveData<List<RaceListEntity>>
        get() {
            return databaseDao.getAllRaces()
        }

    suspend fun refreshRaces() {
        val list = try {
            apiService.getListRaces()
        } catch (e: Exception) {
            Log.e(TAG, "refreshRaces: failed\n${e.stackTraceToString()}")
            return
        }
        val newList = list.results?.mapNotNull {
            it.index?.let { index -> RaceListEntity(index, it.name) }
        } ?: emptyList()
        databaseDao.insertAll(*newList.toTypedArray())
    }

    fun getRace(index: String): LiveData<RaceEntity> {
        return databaseDao.getRace(index)
    }

    suspend fun refreshRaceDetail(index: String): Boolean {
        val race = try {
            apiService.getRaceByIndex(index)
        } catch (e: Exception) {
            Log.e(TAG, "refreshRaceDetail: failed\n${e.stackTraceToString()}")
            return true
        }
        val raceEntity = RaceEntity(index, race.name, race.alignment)
        databaseDao.insertDetail(raceEntity)
        return false
    }

    //class
    val classesList: LiveData<List<ClassListEntity>>
        get() {
            return databaseDao.getAllClasses()
        }

    suspend fun refreshClasses() {
        val list = try {
            apiService.getListClasses()
        } catch (e: Exception) {
            Log.e(TAG, "refreshClasses: failed\n${e.stackTraceToString()}")
            return
        }
        val newList = list.results?.mapNotNull {
            it.index?.let { index -> ClassListEntity(index, it.name) }
        } ?: emptyList()
        databaseDao.saveClassList(*newList.toTypedArray())
    }

    fun getClass(index: String): LiveData<ClassWithProficiencies> {
        return databaseDao.getClass(index)
    }

    suspend fun refreshClassDetail(index: String): Boolean {
        val classDetail = try {
            apiService.getClassByIndex(index)
        } catch (e: Exception) {
            Log.e(TAG, "refreshClasses: failed\n${e.stackTraceToString()}")
            return true
        }
        val classEntity = ClassEntity(index, classDetail.name, classDetail.hitDie)
        val proficiency = classDetail.proficiencies
        val proficiencyEntities = mutableListOf<ProficiencyEntity>()
        val classProficiencyCrossRef = mutableListOf<ClassProficiencyCrossRef>()
        for (it in proficiency) {
            val proficiencyEntity = ProficiencyEntity(it.index, it.name)
            proficiencyEntities.add(proficiencyEntity)
            val crossRef = ClassProficiencyCrossRef(index, it.index)
            classProficiencyCrossRef.add(crossRef)
        }
        databaseDao.saveAllClasses(classEntity)
        databaseDao.saveAllProficiencies(*proficiencyEntities.toTypedArray())
        databaseDao.saveAllClassProficiencyCrossRef(*classProficiencyCrossRef.toTypedArray())
        return false
    }
}