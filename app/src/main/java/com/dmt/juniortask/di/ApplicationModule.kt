package com.dmt.juniortask.di

import androidx.room.Room
import com.dmt.juniortask.AppApplication
import com.dmt.juniortask.db.ServerDatabase
import com.dmt.juniortask.network.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://playground.tesonet.lt/"

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideAppApi(retrofit : Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(application : AppApplication): ServerDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            ServerDatabase::class.java,
            "servers_database")
            .fallbackToDestructiveMigration()
            .build()
    }

}