package com.nextup.spotifyforartists_20.domain.firebase

import com.google.firebase.auth.FirebaseAuth

class UserSessionChecker(private val auth: FirebaseAuth) {

    companion object {
        const val USER_IS_LOGGED_IN = true
        const val USER_IS_NOT_LOGGED_IN = false
    }

    fun check(): Boolean {
        val user = auth.currentUser
        return user != null
    }
}