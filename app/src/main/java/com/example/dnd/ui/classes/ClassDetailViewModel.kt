package com.example.dnd.ui.classes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dnd.database.AppDatabase
import com.example.dnd.database.ClassWithProficiencies
import com.example.dnd.network.RetrofitObject
import com.example.dnd.repository.Repository
import kotlinx.coroutines.launch

class ClassDetailViewModel(application: Application) : AndroidViewModel(application) {

    val database = AppDatabase.getDatabase(application)
    val api = RetrofitObject.service
    val repository = Repository(database, api)

    private var _hasError = MutableLiveData(false)
    val hasError: LiveData<Boolean> get() = _hasError

    fun getClassLiveData(index: String): LiveData<ClassWithProficiencies> {
        viewModelScope.launch {
            val result = repository.refreshClassDetail(index)
            _hasError.value = result
        }
        return repository.getClass(index)
    }
}