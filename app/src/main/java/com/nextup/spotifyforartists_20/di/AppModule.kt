package com.nextup.spotifyforartists_20.di

import android.content.Context
import com.nextup.spotifyforartists_20.domain.network.NetworkConnection
import com.nextup.spotifyforartists_20.domain.user_prefrences.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun networkConnection(app: android.app.Application) : NetworkConnection {
        return NetworkConnection(app)
    }

    @Singleton
    @Provides
    fun provideDataStore(context: Context): DataStore {
        return DataStore(context)
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }



}