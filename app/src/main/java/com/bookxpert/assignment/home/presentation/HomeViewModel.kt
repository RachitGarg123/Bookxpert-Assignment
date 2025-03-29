package com.bookxpert.assignment.home.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import com.bookxpert.assignment.home.domain.GoogleSignIn
import com.bookxpert.assignment.notification.data.NotificationRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
class HomeViewModel /*@Inject constructor(private val repo: NotificationRepository):*/: ViewModel() {
    fun setupGoogleSignIn() {
        GoogleSignIn.setupGoogleSignIn()
    }
    fun tapGoogleSignIn(context: Context, updateUI:(FirebaseUser?)-> Unit) {
        GoogleSignIn.tapGoogleSignIn(context = context) { firebaseUser ->
            updateUI(firebaseUser)
        }
    }
}