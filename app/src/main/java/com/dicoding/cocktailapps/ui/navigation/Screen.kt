package com.dicoding.cocktailapps.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Detail : Screen("detail/{id}") {
        fun createRoute(id: String) = "detail/$id"
    }
    data object Favorite : Screen("favorite")
    data object About : Screen("about")
}