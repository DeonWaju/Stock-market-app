package com.example.stockmarketapp.di

import com.example.stockmarketapp.data.csv.IntradayInfoParser
import com.example.stockmarketapp.domain.model.IntradayInfo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
//
//    @Binds
//    @Singleton
//    abstract fun bindCompanyListingsParser(
//        companyListingsParser: CompanyListingsParser
//    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindIntradayInfoParser(
        intradayInfoParser: IntradayInfoParser
    ): com.example.stockmarketapp.data.csv.CSVParser<IntradayInfo>
//
//    @Binds
//    @Singleton
//    abstract fun bindStockRepository(
//        stockRepositoryImpl: StockRepositoryImpl
//    ): IStockRepository
}