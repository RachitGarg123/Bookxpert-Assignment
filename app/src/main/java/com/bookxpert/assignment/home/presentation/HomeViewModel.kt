package com.bookxpert.assignment.home.presentation

import android.content.Context
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookxpert.assignment.core.AssignmentBookxpertApplication
import com.bookxpert.assignment.core.roomdb.ObjectsDatabase
import com.bookxpert.assignment.core.roomdb.ObjectsEntity
import com.bookxpert.assignment.core.utility.LogType
import com.bookxpert.assignment.core.utility.printLog
import com.bookxpert.assignment.home.data.HomeRepository
import com.bookxpert.assignment.home.data.ObjectResponse
import com.bookxpert.assignment.home.data.Objects
import com.bookxpert.assignment.home.domain.GoogleSignIn
import com.bookxpert.assignment.notification.data.NotificationRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository): ViewModel() {

    val database = ObjectsDatabase.getInstance(AssignmentBookxpertApplication.appContext)
    val objectsDao = database.objectsDao()

    var clicked by mutableStateOf(false)

    var apiResponse by mutableStateOf<MutableList<Objects>>(mutableListOf())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var localDataResponse by mutableStateOf<MutableList<Objects>>(mutableListOf())
        private set

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
            isLoading = true
        }.catch {
            printLog(LogType.ERROR, "HomeViewModel", "exception message ---> ${it.message}")
            isLoading = false
        }.collect {
            isLoading = false
            apiResponse.clear()
            apiResponse.addAll(it)
        }
    }

    private suspend fun insertObjectsData(objects: MutableList<Objects>) = withContext(Dispatchers.IO) {
        printLog(LogType.DEBUG, tag = "roomDB", "3apiResponse ----> $objects")
        objectsDao.insertObjects(ObjectsEntity(objects = objects))
        val insertedData = objectsDao.getAllObjects().firstOrNull()
        printLog(LogType.DEBUG, tag = "roomDB", "Inserted Data ----> $insertedData")
    }

    fun insertAllObjectsData(objects: MutableList<Objects>) = viewModelScope.launch {
        printLog(LogType.DEBUG, tag = "roomDB", "2apiResponse ----> $objects")
        insertObjectsData(objects)
    }

    fun getObjectsFromLocal() = viewModelScope.launch {
//        printLog(LogType.DEBUG, "roomDB", objectsDao.getAllObjects().toString())
            objectsDao.getAllObjects().collect {
            printLog(LogType.DEBUG, "roomDB", "objects ----> ${it.objects}")
//            localDataResponse.clear()
            localDataResponse = it.objects.toMutableList()
        }
    }
}