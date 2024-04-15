package cz.okycelt.pg.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun AppScaffold(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            if (currentDestination?.route != LoginScreenRoute) {
                NavigationBar {
                    val tabs = remember { listOf(Tab.Home, Tab.Account) }

                    tabs.forEach { tab ->
                        NavigationBarItem(
                            icon = { Image(imageVector = tab.icon, contentDescription = null) },
                            label = { Text(text = tab.label) },
                            selected = currentDestination?.hierarchy?.any { it.route == tab.route } == true,
                            onClick = {
                                navController.navigate(tab.route) {
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true
                                    // Restore state when reselecting a previously selected item
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { contentPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(contentPadding)
        )
    }
}

sealed class Tab(val route: String) {
    data object Home : Tab("homeGraph")
    data object Account : Tab("accountGraph")
}

private val Tab.icon: ImageVector
    get() = when (this) {
        is Tab.Home -> Icons.Rounded.Home
        is Tab.Account -> Icons.Rounded.Person
    }

private val Tab.label: String
    get() = when (this) {
        is Tab.Home -> "Home"
        is Tab.Account -> "Account"
    }