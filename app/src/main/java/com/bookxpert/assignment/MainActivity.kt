package com.bookxpert.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bookxpert.assignment.home.presentation.HomeViewModel
import com.bookxpert.assignment.ui.theme.AssignmentBookxpertTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bookxpert.assignment.core.navigation.Screen
import com.bookxpert.assignment.core.navigation.SetupNavGraph
import com.bookxpert.assignment.objectDetails.presentation.ObjectsViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private val objectsViewModel: ObjectsViewModel by viewModels()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        homeViewModel.getUserLoggedIn()
        setContent {
            AssignmentBookxpertTheme {
                navController = rememberNavController()
                LoadUiSetUpNavGraph(
                    navController = navController,
                    homeViewModel = homeViewModel,
                    objectsViewModel = objectsViewModel
                )
            }
        }
        homeViewModel.setupGoogleSignIn()
    }
}

@Composable
fun LoadUiSetUpNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    objectsViewModel: ObjectsViewModel
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val isUserLoggedIn by homeViewModel.isUserLoggedIn.collectAsState()
        if(isUserLoggedIn == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            val startDestination = if (isUserLoggedIn == true) Screen.Home.route else Screen.GoogleSignIn.route
            SetupNavGraph(
                navController = navController,
                homeViewModel = homeViewModel,
                startDestination = startDestination,
                innerPadding = innerPadding,
                objectsViewModel = objectsViewModel
            )
        }
    }
}