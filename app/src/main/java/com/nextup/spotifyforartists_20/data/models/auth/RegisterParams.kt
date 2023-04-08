package com.nextup.spotifyforartists_20.data.models.auth

data class RegisterParams (
    val name: String,
    val lastname: String,
    val email: String,
    val password: String
)