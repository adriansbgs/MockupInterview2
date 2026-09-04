package com.example.parkee.navigation

import kotlinx.serialization.Serializable

@Serializable data object HomeRouteKey
@Serializable data class MovieDetailRouteKey(val movieId: Int)
@Serializable data object FavouriteRouteKey