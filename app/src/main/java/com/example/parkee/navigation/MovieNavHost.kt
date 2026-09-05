package com.example.parkee.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parkee.ui.detail.MovieDetailRoute
import com.example.parkee.ui.favorite.FavoriteRoute
import com.example.parkee.ui.home.HomeRoute

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
            MovieDetailRoute(
                onBackClick = { navController.popBackStack() },
            )
        }
        composable<FavouriteRouteKey> {
            FavoriteRoute(
                onMovieClick = { navController.navigate(MovieDetailRouteKey(it)) },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}