package com.bookxpert.assignment.signIn.domain

import android.app.Activity
import android.content.Context
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.bookxpert.assignment.R
import com.bookxpert.assignment.core.AssignmentBookxpertApplication
import com.bookxpert.assignment.core.utility.LogType
import com.bookxpert.assignment.core.utility.printLog
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object GoogleSignIn {

    private lateinit var request: GetCredentialRequest
    private lateinit var auth: FirebaseAuth
    private const val LOG_TAG = "googleSignIn"

    fun setupGoogleSignIn() {
        // Instantiate a Google sign-in request
        val googleIdOption = GetGoogleIdOption.Builder()
            // Your server's client ID, not your Android client ID.
            .setServerClientId(AssignmentBookxpertApplication.appContext.getString(R.string.web_default_id))
            // Only show accounts previously used to sign in.
            .setFilterByAuthorizedAccounts(false)
            .build()

        // Create the Credential Manager request
        request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
        auth = Firebase.auth
    }

    private fun authorizeCredentials(context: Context, credential: Credential, updateUI: (FirebaseUser?) -> Unit) {
        try {
            // Check if credential is of type Google ID
            if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                // Create Google ID Token
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                // Sign in to Firebase with using the token
                firebaseAuthWithGoogle(context, googleIdTokenCredential.idToken) { firebaseUser ->
                    updateUI(firebaseUser)
                }
            } else {
                updateUI(null)
                printLog(LogType.WARN, LOG_TAG, "exception message --> Credential is not of type Google ID!")
            }
        } catch (e: Exception) {
            updateUI(null)
            printLog(LogType.ERROR, LOG_TAG, "exception message--> ${e.message}")
        }
    }

    fun tapGoogleSignIn(context: Context, updateUI: (FirebaseUser?) -> Unit) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val credentialManager = CredentialManager.create(AssignmentBookxpertApplication.appContext)
                val request = async {
                    try {
                        credentialManager.getCredential(
                            request = request,
                            context = context
                        )
                    } catch (e:Exception) {
                        printLog(LogType.ERROR, LOG_TAG, "exception message --> ${e.message}")
                        null
                    }
                }
                val credentials = request.await()?.credential
                if(credentials != null) {
                    authorizeCredentials(context, credentials) { firebaseUer ->
                        updateUI(firebaseUer)
                    }
                } else {
                    updateUI(null)
                }
            }
        } catch (e: Exception) {
            printLog(LogType.ERROR, LOG_TAG, "exception message --> ${e.message}")
            updateUI(null)
        }
    }

    private fun firebaseAuthWithGoogle(context: Context, idToken: String, updateUI: (FirebaseUser?) -> Unit) {
        try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            auth.signInWithCredential(credential)
                .addOnCompleteListener(context as Activity) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        updateUI(null)
                    }
                }
                .addOnFailureListener {
                    updateUI(null)
                }
        } catch (e: Exception) {
            updateUI(null)
            printLog(LogType.ERROR, LOG_TAG, "exception message --> ${e.message}")
        }
    }
}