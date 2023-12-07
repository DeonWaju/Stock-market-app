package com.example.stockmarketapp.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

//    @Binds
//    @Singleton
//    abstract fun bindCompanyListingParser(companyListingParser: CompanyListingsParser)
//    : ICSVParser<CompanyListing>
}