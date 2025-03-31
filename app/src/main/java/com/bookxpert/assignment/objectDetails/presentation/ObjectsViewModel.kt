package com.bookxpert.assignment.objectDetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookxpert.assignment.core.networking.ApiInterface
import com.bookxpert.assignment.core.roomdb.ObjectsDao
import com.bookxpert.assignment.home.data.Objects
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ObjectsViewModel @Inject constructor(private val apiInterface: ApiInterface, private val objectsDao: ObjectsDao): ViewModel() {

    private var _localDataResponse = MutableStateFlow<MutableList<Objects>>(mutableListOf())
    val localDataResponse: StateFlow<MutableList<Objects>> = _localDataResponse

    fun getObjectsFromLocal() = viewModelScope.launch {
        objectsDao.getAllObjects()?.collect {
            _localDataResponse.value = it?.objects?.toMutableList() ?: mutableListOf()
        }
    }

    fun editLocalObjectData(objects: Objects) = viewModelScope.launch {
        objects.id?.let {
            objectsDao.updateObjectById(it, objects)
            val updatedList = localDataResponse.value.map {
                if (it.id == objects.id) it.copy(data = objects.data) else it
            }.toMutableList()
            _localDataResponse.value = updatedList
        }
    }

    fun deleteLocalObjectData(objects: Objects) = viewModelScope.launch {
        objects.id?.let {
            objectsDao.deleteObjectById(it)
            val updatedList = localDataResponse.value.filter {
                it.id != objects.id
            }.toMutableList()
            _localDataResponse.value = updatedList
        }
    }
}