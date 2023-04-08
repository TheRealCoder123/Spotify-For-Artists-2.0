package com.nextup.spotifyforartists_20.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nextup.spotifyforartists_20.domain.firebase.ArtistIdProvider
import com.nextup.spotifyforartists_20.domain.firebase.UserSessionChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() : FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirestore() : FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideUserSessionChecker(auth: FirebaseAuth) : UserSessionChecker {
        return UserSessionChecker(auth)
    }

    @Provides
    @Singleton
    fun provideArtistIdProvider(auth: FirebaseAuth) : ArtistIdProvider {
        return ArtistIdProvider(auth)
    }

}