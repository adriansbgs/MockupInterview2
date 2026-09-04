package com.example.parkee.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parkee.HomeRoute

@Composable
fun MovieNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = HomeRouteKey) {
        composable<HomeRouteKey> {
            HomeRoute(
                onMovieClick = { navController.navigate(MovieDetailRouteKey(it)) },
                onFavoriteListClick = { navController.navigate(FavouriteRouteKey) }
            )
        }
        composable<MovieDetailRouteKey> {
            // TODO: MovieDetailRoute
        }
        composable<FavouriteRouteKey> {
            // TODO: FavoriteRoute
        }
    }
}