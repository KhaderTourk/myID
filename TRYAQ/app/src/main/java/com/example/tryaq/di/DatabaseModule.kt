package com.example.tryaq.di

import android.content.Context
import androidx.room.Room
import com.example.tryaq.data.local.TryaqDatabase
import com.example.tryaq.data.repository.LocalDataSourceImpl
import com.example.tryaq.domain.repository.LocalDataSource
import com.example.tryaq.util.Constants.TRYAQ_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        TryaqDatabase::class.java,
        TRYAQ_DATABASE
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: TryaqDatabase) = database.tryaqDao()

    @Singleton
    @Provides
    fun provideKeyDao(database: TryaqDatabase) = database.tryaqRemoteKeysDao()

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: TryaqDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(
            tryaqDatabase = database
        )
    }
}