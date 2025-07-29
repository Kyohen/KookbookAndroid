package kyo.hen.kookbook.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kyo.hen.kookbook.features.Creation.CreationScreen
import kyo.hen.kookbook.features.Creation.CreationScreenDestination
import kyo.hen.kookbook.features.Detail.DetailRoute
import kyo.hen.kookbook.features.Detail.DetailedScreen
import kyo.hen.kookbook.features.Home.HomeScreen

sealed class TabDestination(val route: String, val label: String, val icon: ImageVector) {
    data object Home: TabDestination(route = "Home", label = "Home", icon = Icons.Filled.Home)
    data object Favourites: TabDestination(route = "Favourites", label = "Favourites", icon = Icons.Filled.Favorite)
}

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = TabDestination.Home.route
    val tabs = listOf(TabDestination.Home, TabDestination.Favourites)
//    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                tabs.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = currentRoute?.hierarchy?.any { it.hasRoute(destination.route::class) } == true,
                        onClick = {
                            navController.navigate(route = destination.route) {
                                // Pop up to the start destination to avoid a large back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                // Avoid multiple copies of the same destination when reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                destination.icon,
                                contentDescription = "destination.contentDescription"
                            )
                        },
                        label = { Text(destination.label) }
                    )
                }
            }
        }
    ) { contentPadding ->
        NavHost(
            navController = navController, startDestination = startDestination) {

            composable(TabDestination.Home.route) {
                HomeScreen(
                    padding = contentPadding,
                    navigateToDetailedView = { navController.navigate(DetailRoute(it)) },
                    navigateToAddRecipeView = { navController.navigate(CreationScreenDestination)}

                )
            }

            composable(TabDestination.Favourites.route) {
                Greeting("Android Faves", modifier = Modifier.padding(contentPadding))
            }

            composable<DetailRoute> {
                DetailedScreen(contentPadding, navigateBack = {
                    navController.popBackStack()
                })
            }

            composable<CreationScreenDestination> {
                CreationScreen(
                    paddingValues = contentPadding,
                    navigateBack = { navController.popBackStack() }
                    )
            }
        }

//        AppNavHost(navController, startDestination, modifier = Modifier.padding(contentPadding))
    }
}