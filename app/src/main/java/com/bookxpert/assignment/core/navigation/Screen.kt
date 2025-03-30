package com.bookxpert.assignment.core.navigation

sealed class Screen(val route: String) {

    data object Home: Screen(route = "home_screen")

    data object GoogleSignIn: Screen(route = "google_sign_in_screen")

    data object Objects: Screen(route = "login_screen")
}