package com.dicoding.cocktailapps.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail") {
        fun createRoute(id: String) = "detail/$id"
    }
    object Favorite : Screen("favorite") {
        fun createRoute(id: String) = "favorite/$id"
    }
    object About : Screen("about")
}