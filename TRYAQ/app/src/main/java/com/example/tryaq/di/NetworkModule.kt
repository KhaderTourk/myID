package com.example.tryaq.di

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import com.example.tryaq.data.local.TryaqDatabase
import com.example.tryaq.data.remote.TryaqApi
import com.example.tryaq.data.repository.RemoteDataSourceImpl
import com.example.tryaq.domain.repository.RemoteDataSource
import com.example.tryaq.util.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideNetworkInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor() }

    @Provides
    @Singleton
    fun provideHttpClient(networkInterceptor: HttpLoggingInterceptor): OkHttpClient {
        networkInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build() }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create() }

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideTryaqApi(retrofit: Retrofit): TryaqApi {
        return retrofit.create(TryaqApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        tryaqApi: TryaqApi,
        tryaqDatabase: TryaqDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            tryaqApi = tryaqApi,
            tryaqDatabase = tryaqDatabase
        )
    }

}