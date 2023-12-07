package com.example.stockmarketapp.presentation.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stockmarketapp.presentation.companylist.CompanyListingScreen

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Route.CompanyListingScreen.route) {
            CompanyListingScreen()
        }
//        composable(route = Route.CompanyInfoScreen.route) {
//            CompanyInfoScreen()
//        }
    }
}