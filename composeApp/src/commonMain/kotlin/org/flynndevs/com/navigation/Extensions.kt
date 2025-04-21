package org.flynndevs.com.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.flynndevs.com.feature.home.HomeScreen
import org.flynndevs.com.feature.home.HomeViewModel
import org.flynndevs.com.feature.map.MapScreen
import org.flynndevs.com.feature.map.MapViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        homeScreen(navController)
        mapScreen(navController)
    }
}

fun NavGraphBuilder.homeScreen(navController: NavController) {
    composable("home") {
        val viewModel: HomeViewModel = it.sharedKoinViewModel(navController)
        HomeScreen(viewModel)
    }
}

fun NavGraphBuilder.mapScreen(navController: NavController) {
    composable("map") {
        val viewModel: MapViewModel = it.sharedKoinViewModel(navController)
        MapScreen(viewModel, navController)
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}
