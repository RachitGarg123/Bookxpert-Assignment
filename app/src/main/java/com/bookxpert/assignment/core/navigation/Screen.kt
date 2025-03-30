package com.bookxpert.assignment.core.navigation

const val DEFAULT_ARGUMENT_KEY = "id"
const val DEFAULT_ARGUMENT_KEY2 = "name"

sealed class Screen(val route: String) {

    data object Home: Screen(route = "home_screen")

    data object Detail: Screen(route = "detail_screen")

    data object Login: Screen(route = "login_screen")

    data object Signup: Screen(route = "signup_screen")
}