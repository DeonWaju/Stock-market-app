package com.example.stockmarketapp.domain.repository

import com.example.stockmarketapp.domain.model.CompanyInfo
import com.example.stockmarketapp.domain.model.CompanyListing
import com.example.stockmarketapp.domain.model.IntradayInfo
import com.example.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepositoryRDS {
    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyInfo>

    suspend fun getIntraDayInfo(
        symbol: String
    ): Resource<List<IntradayInfo>>
}