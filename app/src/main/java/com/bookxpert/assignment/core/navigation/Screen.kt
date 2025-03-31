package com.bookxpert.assignment.core.navigation

const val CAMERAX_PREVIEW_KEY = "camerax_preview_key"
const val OBJECT_SCREEN_KEY = "object_screen_key"

sealed class Screen(val route: String) {

    data object Home: Screen(route = "home_screen")

    data object GoogleSignIn: Screen(route = "google_sign_in_screen")

    data object Objects: Screen(route = "objects_screen")

    data object PdfViewer: Screen(route = "pdf_viewer_screen")
}