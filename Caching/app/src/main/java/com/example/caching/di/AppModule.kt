package com.example.caching.di

import android.app.Application
import androidx.room.Room
import com.example.caching.api.PartAPI
import com.example.caching.data.PartDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(PartAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providePartApi(retrofit: Retrofit): PartAPI =
        retrofit.create(PartAPI::class.java)

    @Provides
    @Singleton
    fun provideDatabae(app: Application): PartDataBase =
        Room.databaseBuilder(app,PartDataBase::class.java , "part_database")
            .build()
}