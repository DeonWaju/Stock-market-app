package com.example.stockmarketapp.data.remote.dto

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {
    @GET
    suspend fun fetchListings(
        @Query("apiKey") apiKey: String
    ): ResponseBody
}