package com.bookxpert.assignment.core.navigation

const val CAMERAX_PREVIEW_KEY = "camerax_preview_key"

sealed class Screen(val route: String) {

    data object Home: Screen(route = "home_screen")

    data object GoogleSignIn: Screen(route = "google_sign_in_screen")

    data object Objects: Screen(route = "login_screen")

    data object CameraXPreview: Screen(route = "camera_preview_screen/{$CAMERAX_PREVIEW_KEY}")

    data object PdfViewer: Screen(route = "pdf_viewer_screen")
}