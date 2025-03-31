package com.bookxpert.assignment.core.navigation

import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bookxpert.assignment.captureImage.presentation.CameraGallerySelector
import com.bookxpert.assignment.captureImage.presentation.CameraPreviewScreen
//import com.bookxpert.assignment.captureImage.presentation.CameraPreviewScreen
import com.bookxpert.assignment.home.presentation.HomeScreen
import com.bookxpert.assignment.signIn.presentation.GoogleSignInScreen
import com.bookxpert.assignment.home.presentation.HomeViewModel
import com.bookxpert.assignment.objectDetails.presentation.ObjectDataScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    startDestination: String,
    innerPadding: PaddingValues,
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
                homeViewModel = homeViewModel,
                innerPadding = innerPadding
            )
        }
        composable(
            route = Screen.CameraGallery.route,
        ) {
            CameraGallerySelector(navController)
        }
        composable(
            route = Screen.CameraXPreview.route,
        ) {
            CameraPreviewScreen(navController, innerPadding)
        }
    }
}