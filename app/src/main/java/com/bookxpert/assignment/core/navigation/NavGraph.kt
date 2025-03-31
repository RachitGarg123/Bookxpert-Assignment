package com.bookxpert.assignment.core.navigation

import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bookxpert.assignment.captureImage.presentation.CameraPreviewScreen
import com.bookxpert.assignment.home.data.Objects
import com.bookxpert.assignment.home.presentation.HomeScreen
import com.bookxpert.assignment.signIn.presentation.GoogleSignInScreen
import com.bookxpert.assignment.home.presentation.HomeViewModel
import com.bookxpert.assignment.objectDetails.presentation.ObjectDataScreen
import com.bookxpert.assignment.objectDetails.presentation.ObjectsViewModel
import com.bookxpert.assignment.pdfViewer.presentation.PdfViewer

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    startDestination: String,
    innerPadding: PaddingValues,
    objectsViewModel: ObjectsViewModel
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
            route = "objects_screen/{$OBJECT_SCREEN_KEY}",
            arguments = listOf(
                navArgument(OBJECT_SCREEN_KEY) {
                    type = NavType.BoolType
                }
            )
        ) {
            ObjectDataScreen(
                navHostController = navController,
                innerPadding = innerPadding,
                objectsViewModel = objectsViewModel,
                pushNotificationEnabled = it.arguments?.getBoolean(OBJECT_SCREEN_KEY)
            )
        }
        composable(
            route = "camera_preview_screen/{$CAMERAX_PREVIEW_KEY}",
            arguments = listOf(
                navArgument(CAMERAX_PREVIEW_KEY) {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { navBackStackEntry ->
            val encodedUri = navBackStackEntry.arguments?.getString(CAMERAX_PREVIEW_KEY)
            val imageUri = encodedUri?.let { Uri.parse(it) }
            CameraPreviewScreen(navController, innerPadding, imageUri)
        }
        composable(
            route = Screen.PdfViewer.route
        ) {
            PdfViewer(innerPadding)
        }
    }
}