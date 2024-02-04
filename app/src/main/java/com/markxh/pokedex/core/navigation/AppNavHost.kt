package com.markxh.pokedex.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.markxh.pokedex.ui.feature.dashboard.presentation.DashboardScreen
import com.markxh.pokedex.ui.feature.detail.presentation.DetailScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier, startDestination: String = Route.DASHBOARD) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Route.DASHBOARD) { DashboardScreen() }
        composable(Route.DETAIL) { DetailScreen() }
    }
}