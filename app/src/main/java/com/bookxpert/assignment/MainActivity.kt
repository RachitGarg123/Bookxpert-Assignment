package com.bookxpert.assignment

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bookxpert.assignment.home.presentation.HomeScreen
import com.bookxpert.assignment.home.presentation.HomeViewModel
import com.bookxpert.assignment.signIn.presentation.GoogleSignInScreen
import com.bookxpert.assignment.ui.theme.AssignmentBookxpertTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel = HomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AssignmentBookxpertTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var clicked by remember { mutableStateOf(false) }
                    GoogleSignInScreen(
                        clicked = clicked,
                         onGoogleSignIn = {
                            homeViewModel.tapGoogleSignIn(this) { firebaseUser ->
                                if(firebaseUser == null) {
                                    clicked = false
                                }
                            }
                        },
                        onGoogleSignInButtonClicked = {
                            clicked = true
                        }
                    )
                }
            }
        }
        homeViewModel.setupGoogleSignIn()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AssignmentBookxpertTheme {

    }
}