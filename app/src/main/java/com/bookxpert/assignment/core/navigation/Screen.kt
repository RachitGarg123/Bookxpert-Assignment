package com.bookxpert.assignment.core.navigation

sealed class Screen(val route: String) {

    data object Home: Screen(route = "home_screen")

    data object GoogleSignIn: Screen(route = "google_sign_in_screen")

    data object Objects: Screen(route = "login_screen")

    data object CameraGallery: Screen(route = "camera_gallery_screen")

    data object CameraXPreview: Screen(route = "camera_preview_screen")
}