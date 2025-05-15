package com.example.dnd.ui.races

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dnd.database.AppDatabase
import com.example.dnd.database.RaceEntity
import com.example.dnd.network.RetrofitObject
import com.example.dnd.repository.Repository
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    val database = AppDatabase.getDatabase(application)
    val api = RetrofitObject.service
    val repository = Repository(database, api)

    private var _hasError = MutableLiveData(false)
    val hasError: LiveData<Boolean> get() = _hasError

    fun getRaceLiveData(index: String): LiveData<RaceEntity> {
        viewModelScope.launch {
            val result = repository.refreshRaceDetail(index)
            _hasError.value = result
        }
        return repository.getRace(index)
    }
}