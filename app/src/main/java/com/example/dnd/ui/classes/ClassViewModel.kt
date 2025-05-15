package com.example.dnd.ui.classes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.dnd.database.AppDatabase
import com.example.dnd.database.ClassListEntity
import com.example.dnd.network.RetrofitObject
import com.example.dnd.repository.Repository
import kotlinx.coroutines.launch

class ClassViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val api = RetrofitObject.service
    private val repository = Repository(database, api)

    val list: LiveData<List<ClassListEntity>> = repository.classesList

    val isNoData: LiveData<Boolean> = list.map { classes: List<ClassListEntity> ->
        classes.isEmpty()
    }

    init {
        viewModelScope.launch {
            repository.refreshClasses()
        }
    }
}
