package cz.okycelt.pg.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import cz.okycelt.pg.navigation.screens.AccountScreen
import cz.okycelt.pg.navigation.screens.DetailScreen
import cz.okycelt.pg.navigation.screens.HomeScreen
import cz.okycelt.pg.navigation.screens.LoginScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = Tab.Home.route
    ) {
        navigation(
            route = Tab.Home.route,
            startDestination = HomeScreen.Home.route
        ) {
            composable(
                route = HomeScreen.Home.route
            ) {
                HomeScreen(
                    navigateToDetail = { navController.navigate(HomeScreen.Detail.route) }
                )
            }

            composable(
                route = HomeScreen.Detail.route
            ) {
                DetailScreen(
                    navigateBack = navController::navigateUp
                )
            }
        }

        navigation(
            route = Tab.Account.route,
            startDestination = AccountScreen.Account.route
        ) {
            composable(
                route = AccountScreen.Account.route
            ) {
                AccountScreen(
                    logOut = {
                        navController.clearBackStack(Tab.Home.route)
                        navController.clearBackStack(Tab.Account.route)

                        navController.navigate(LoginScreenRoute) {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }

        composable(
            route = LoginScreenRoute
        ) {
            LoginScreen(
                logIn = {
                    navController.navigate(Tab.Home.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

private sealed class HomeScreen(val route: String) {
    data object Home : HomeScreen("home")
    data object Detail : HomeScreen("detail")
}

private sealed class AccountScreen(val route: String) {
    data object Account : AccountScreen("account")
}

const val LoginScreenRoute = "login"