package com.example.stockmarketapp.presentation.navGraph

sealed class Route(
    val route: String
) {
    object CompanyListingScreen: Route(route = "companyListingScreen")
    object CompanyInfoScreen: Route(route = "companyInfoScreen")
}