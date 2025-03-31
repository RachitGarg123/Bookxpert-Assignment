package com.bookxpert.assignment.home.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookxpert.assignment.core.AssignmentBookxpertApplication
import com.bookxpert.assignment.core.roomdb.ObjectsDao
import com.bookxpert.assignment.core.roomdb.ObjectsDatabase
import com.bookxpert.assignment.core.roomdb.ObjectsEntity
import com.bookxpert.assignment.home.data.HomeRepository
import com.bookxpert.assignment.home.data.Objects
import com.bookxpert.assignment.home.domain.GoogleSignIn
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository, private val objectsDao: ObjectsDao): ViewModel() {

    private var _objectsResponse = MutableStateFlow<MutableList<Objects>>(mutableListOf())
    val objectsResponse: StateFlow<MutableList<Objects>>  = _objectsResponse

    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var _isUserLoggedIn = MutableStateFlow<Boolean?>(null)
    val isUserLoggedIn: StateFlow<Boolean?> = _isUserLoggedIn

    fun setupGoogleSignIn() {
        GoogleSignIn.setupGoogleSignIn()
    }
    fun tapGoogleSignIn(context: Context, updateUI:(FirebaseUser?)-> Unit) {
        GoogleSignIn.tapGoogleSignIn(context = context) { firebaseUser ->
            updateUI(firebaseUser)
        }
    }

    fun getObjects() = viewModelScope.launch {
        homeRepository.getObjects().onStart {
            _isLoading.value = true
        }.catch {
            _isLoading.value = false
            _objectsResponse.value.clear()
        }.collect {
            _isLoading.value = false
            _objectsResponse.value.clear()
            _objectsResponse.value.addAll(it)
        }
    }

    private suspend fun insertObjectsData(objects: MutableList<Objects>) = withContext(Dispatchers.IO) {
        objectsDao.insertObjects(ObjectsEntity(objects = objects))
        val insertedData = objectsDao.getAllObjects()?.firstOrNull()
    }

    fun insertAllObjectsData(objects: MutableList<Objects>) = viewModelScope.launch {
        insertObjectsData(objects)
    }


    fun getUserLoggedIn() = viewModelScope.launch {
        homeRepository.isUserLoggedIn.collect {
            _isUserLoggedIn.value = it
        }
    }

    fun userLoggedIn(isUserLoggedIn: Boolean) = viewModelScope.launch {
        homeRepository.updateLoggedInState(isUserLoggedIn)
    }
}