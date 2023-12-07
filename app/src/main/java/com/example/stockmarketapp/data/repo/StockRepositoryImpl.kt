package com.example.stockmarketapp.data.repo

import com.example.stockmarketapp.data.local.dto.StockDatabase
import com.example.stockmarketapp.data.mapper.toCompanyListing
import com.example.stockmarketapp.data.remote.dto.StockApi
import com.example.stockmarketapp.domain.model.CompanyInfo
import com.example.stockmarketapp.domain.model.CompanyListing
import com.example.stockmarketapp.domain.model.IntradayInfo
import com.example.stockmarketapp.domain.repository.StockRepositoryRDS
import com.example.stockmarketapp.util.Resource
import com.opencsv.CSVParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StockRepositoryImpl(
    private val api: StockApi,
    private val db: StockDatabase,
//    private val companyListingParser: CSVParser<CompanyListing>
): StockRepositoryRDS {
    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = db.dao.searchCompanyListing(query)
            emit(
                Resource.Success(
                    data = localListing.map { it.toCompanyListing() }
                ))

            val isDbEmpty = localListing.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache){
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfo> {
        TODO("Not yet implemented")
    }

    override suspend fun getIntraDayInfo(symbol: String): Resource<List<IntradayInfo>> {
        TODO("Not yet implemented")
    }


}