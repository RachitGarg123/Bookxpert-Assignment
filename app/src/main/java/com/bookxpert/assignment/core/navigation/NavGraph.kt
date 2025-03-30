package com.bookxpert.assignment.core.navigation

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.compose.composable
import com.bookxpert.assignment.home.presentation.HomeScreen
import com.bookxpert.assignment.signIn.presentation.GoogleSignInScreen


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.bookxpert.assignment.core.AssignmentBookxpertApplication
import com.bookxpert.assignment.core.utility.showToast
import com.bookxpert.assignment.home.presentation.HomeViewModel

@Composable
fun SetupNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    homeViewModel: HomeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Detail.route,
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navHostController = navController)
        }
        composable(
            route = Screen.Detail.route,
        ) {
            GoogleSignInScreen(
                navHostController = navController,
                homeViewModel = homeViewModel
            )
        }
    }
}