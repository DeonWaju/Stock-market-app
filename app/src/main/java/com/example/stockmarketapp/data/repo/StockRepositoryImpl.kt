package com.example.stockmarketapp.data.repo

import com.example.stockmarketapp.data.csv.CSVParser
import com.example.stockmarketapp.data.local.dto.StockDatabase
import com.example.stockmarketapp.data.mapper.toCompanyListing
import com.example.stockmarketapp.data.remote.dto.StockApi
import com.example.stockmarketapp.domain.model.CompanyInfo
import com.example.stockmarketapp.domain.model.CompanyListing
import com.example.stockmarketapp.domain.model.IntradayInfo
import com.example.stockmarketapp.domain.repository.StockRepositoryRDS
import com.example.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.io.InputStreamReader

class StockRepositoryImpl(
    private val api: StockApi,
    private val db: StockDatabase,
    private val companyListingParser: CSVParser<CompanyListing>
) : StockRepositoryRDS {
    private val dao = db.dao
    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchCompanyListing(query)
            emit(
                Resource.Success(
                    data = localListings.map { it.toCompanyListing() }
                ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = api.getListings()
                val csvReader = companyListingParser.parse(response.byteStream())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldnt load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldnt load data"))
                null
            }

            remoteListings?.let { listings ->
                dao.clearCompanyListings()
                dao.insertCompanyListings(
                    listings.map { }
                )
            }
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfo> {
        TODO("Not yet implemented")
    }

    override suspend fun getIntraDayInfo(symbol: String): Resource<List<IntradayInfo>> {
        TODO("Not yet implemented")
    }


}