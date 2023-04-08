package com.nextup.spotifyforartists_20.domain.firebase

import com.google.firebase.auth.FirebaseAuth

class ArtistIdProvider(private val firebaseAuth: FirebaseAuth) {
    fun get(): String {
        return "${firebaseAuth.currentUser!!.uid}-artist"
    }
}