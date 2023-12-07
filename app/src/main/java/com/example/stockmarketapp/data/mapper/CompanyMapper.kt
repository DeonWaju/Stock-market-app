package com.example.stockmarketapp.data.mapper

import com.example.stockmarketapp.data.local.dto.CompanyListingEntity
import com.example.stockmarketapp.domain.model.CompanyInfo
import com.example.stockmarketapp.domain.model.CompanyListing


fun CompanyListingEntity.toCompanyListing(): CompanyListing =
    CompanyListing(name, symbol, exchange)

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity =
    CompanyListingEntity(name, symbol, exchange)

//fun CompanyInfoDto.toCompanyInfo(): CompanyInfo { }