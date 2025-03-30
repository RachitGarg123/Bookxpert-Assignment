package com.bookxpert.assignment.home.presentation

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bookxpert.assignment.home.domain.GoogleSignIn
import com.bookxpert.assignment.notification.data.NotificationRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(val repo: NotificationRepository): ViewModel() {

    var clicked by mutableStateOf(false)

    fun setupGoogleSignIn() {
        GoogleSignIn.setupGoogleSignIn()
    }
    fun tapGoogleSignIn(context: Context, updateUI:(FirebaseUser?)-> Unit) {
        GoogleSignIn.tapGoogleSignIn(context = context) { firebaseUser ->
            updateUI(firebaseUser)
        }
    }
}