package com.example.dnd.ui.races

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.dnd.database.AppDatabase
import com.example.dnd.database.RaceListEntity
import com.example.dnd.network.RetrofitObject
import com.example.dnd.repository.Repository
import kotlinx.coroutines.launch

class RaceViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getDatabase(application)
    private val api = RetrofitObject.service
    private val repository = Repository(database, api)

    val list: LiveData<List<RaceListEntity>> = repository.racesList

    val isNoData: LiveData<Boolean> = list.map { races: List<RaceListEntity> ->
        races.isEmpty()
    }

    init {
        viewModelScope.launch {
            repository.refreshRaces()
        }
    }


}