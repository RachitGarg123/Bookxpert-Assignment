package com.bookxpert.assignment.home.presentation

import android.content.Context
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookxpert.assignment.core.utility.LogType
import com.bookxpert.assignment.core.utility.printLog
import com.bookxpert.assignment.home.data.HomeRepository
import com.bookxpert.assignment.home.data.ObjectResponse
import com.bookxpert.assignment.home.data.Objects
import com.bookxpert.assignment.home.domain.GoogleSignIn
import com.bookxpert.assignment.notification.data.NotificationRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository): ViewModel() {

    var clicked by mutableStateOf(false)

    var apiResponse by mutableStateOf<MutableList<Objects>>(mutableListOf())
        private set

    var isLoading by mutableStateOf(false)
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
}