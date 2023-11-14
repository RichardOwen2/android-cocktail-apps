package com.dicoding.cocktailapps

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.cocktailapps.ui.navigation.Screen
import com.dicoding.cocktailapps.ui.screen.about.AboutScreen
import com.dicoding.cocktailapps.ui.screen.detail.DetailScreen
import com.dicoding.cocktailapps.ui.screen.favorite.FavoriteScreen
import com.dicoding.cocktailapps.ui.screen.home.HomeScreen
import com.dicoding.cocktailapps.ui.theme.CocktailAppsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Centered Top App Bar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
//                navigationIcon = {
//                    IconButton(onClick = { /* do something */ }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Localized description"
//                        )
//                    }
//                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onNavigateToDetailScreen = { id ->
                        navController.navigate(Screen.Detail.createRoute(id))
                    },
                    onNavigateToFavoriteScreen = {
                        navController.navigate(Screen.Favorite.route)
                    },
                    onNavigateToAboutScreen = {
                        navController.navigate(Screen.About.route)
                    }
                )
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen()
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) {
                val id = it.arguments?.getString("id") ?: ""
                DetailScreen(id)
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AppPreview() {
//    CocktailAppsTheme {
//        App()
//    }
//}
