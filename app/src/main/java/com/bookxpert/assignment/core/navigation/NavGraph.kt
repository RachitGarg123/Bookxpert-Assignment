package com.bookxpert.assignment.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bookxpert.assignment.home.presentation.HomeScreen
import com.bookxpert.assignment.signIn.presentation.GoogleSignInScreen
import com.bookxpert.assignment.home.presentation.HomeViewModel
import com.bookxpert.assignment.objectDetails.presentation.ObjectDataScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = Screen.GoogleSignIn.route,
        ) {
            GoogleSignInScreen(
                navHostController = navController,
                homeViewModel = homeViewModel
            )
        }
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(
                navHostController = navController,
                homeViewModel = homeViewModel
            )
        }
        composable(
            route = Screen.Objects.route,
        ) {
            ObjectDataScreen(
                navHostController = navController,
                homeViewModel = homeViewModel
            )
        }
    }
}