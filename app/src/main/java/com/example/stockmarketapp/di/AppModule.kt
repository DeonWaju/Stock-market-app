package com.example.stockmarketapp.di

import android.app.Application
import androidx.room.Room
import com.example.stockmarketapp.data.csv.CSVParser
import com.example.stockmarketapp.data.csv.CompanyListingsParser
import com.example.stockmarketapp.data.local.dto.StockDatabase
import com.example.stockmarketapp.data.remote.dto.StockApi
import com.example.stockmarketapp.data.repo.StockRepositoryImpl
import com.example.stockmarketapp.domain.model.CompanyListing
import com.example.stockmarketapp.domain.repository.IStockRepository
import com.example.stockmarketapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockApi(): StockApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    }).build()
            )
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideStockDatabase(application: Application): StockDatabase {
        return Room.databaseBuilder(
            application,
            StockDatabase::class.java,
            "stockapp.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideStockRepository(
        api: StockApi,
        db: StockDatabase,
        icsvParser: CSVParser<CompanyListing>
    ): IStockRepository = StockRepositoryImpl(api, db, icsvParser)

    @Provides
    @Singleton
    fun provideCsvParser(): CSVParser<CompanyListing> = CompanyListingsParser()

}