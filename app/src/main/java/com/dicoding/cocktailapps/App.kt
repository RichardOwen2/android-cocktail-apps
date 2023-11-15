package com.dicoding.cocktailapps

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.cocktailapps.data.common.MenuItem
import com.dicoding.cocktailapps.ui.navigation.Screen
import com.dicoding.cocktailapps.ui.screen.about.AboutScreen
import com.dicoding.cocktailapps.ui.screen.detail.DetailScreen
import com.dicoding.cocktailapps.ui.screen.favorite.FavoriteScreen
import com.dicoding.cocktailapps.ui.screen.home.HomeScreen
import com.dicoding.cocktailapps.ui.theme.CocktailAppsTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val menuItem = listOf(
        MenuItem(
            title = "Home",
            icon = Icons.Default.Home,
            route = Screen.Home.route
        ),
        MenuItem(
            title = "Favorite",
            icon = Icons.Default.Favorite,
            route = Screen.Favorite.route
        ),
        MenuItem(
            title = "About",
            icon = Icons.Default.Info,
            route = Screen.About.route
        ),
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            if (drawerState.isClosed) {
                                drawerState.open()
                            } else {
                                drawerState.close()
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                },
                title = {
                    Text(stringResource(R.string.app_name))
                },
            )
        },
    ) { innerPadding ->
        ModalNavigationDrawer(
            modifier = Modifier.padding(innerPadding),
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(Modifier.height(12.dp))
                    menuItem.forEach { item ->
                        NavigationDrawerItem(
                            icon = { Icon(item.icon, contentDescription = null) },
                            label = { Text(item.title) },
                            selected = currentRoute == item.route,
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                }
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            },
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                    }
                }
            },
            content = {
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route,
                ) {
                    composable(Screen.Home.route) {
                        HomeScreen(
                            onNavigateToDetailScreen = { id ->
                                navController.navigate(Screen.Detail.createRoute(id))
                            },
                        )
                    }
                    composable(Screen.Favorite.route) {
                        FavoriteScreen(
                            onNavigateToDetailScreen = { id ->
                                navController.navigate(Screen.Detail.createRoute(id))
                            },
                        )
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
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    CocktailAppsTheme {
        App()
    }
}
